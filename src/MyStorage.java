
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;



public class MyStorage {

    private byte[] file;
    private String title;
    private String topic;
    private int id;
    private String param;
    private int point;
    private int point2;
    private String client;

    // Change these paths to store the content in your file system.
    private String topicPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Topics/";
    private String titlesPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Titles/";
    private String filesPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Files/";


    public MyStorage(Content someContent) {
        this.file = someContent.getFile();
        this.title = someContent.getTitle();
        this.topic = someContent.getTopic();
        this.id = someContent.getId();
        this.client = someContent.getClient();

    }
    public MyStorage(){}

    public MyStorage(String param) {
        this.param = param;
    }

    public MyStorage(String original, String client) {
        this.title = original;
        this.client = client;
    }


    public void saveContent() {


        try (FileOutputStream fos = new FileOutputStream(filesPath + id)) {
            fos.write(file);


        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    public void saveTitle() {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(titlesPath, true));
            writer.write(this.id + ":" + this.title + " - uploaded by  " + this.client);
            writer.newLine();

            writer.close();

            System.out.println("Title stored correctly!");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public void saveTopics() {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(topicPath, true));
            writer.write(this.topic + ":" + this.id);
            writer.newLine();

            writer.close();
            System.out.println("Topic stored correctly!");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public ArrayList<String> load() {
        ArrayList<String> identifiers = new ArrayList<String>();

        try {

            Scanner txtscan = new Scanner(new File(topicPath));

            while (txtscan.hasNextLine()) {
                String str = txtscan.nextLine();
                if (str.indexOf(param) != -1) {

                    point = str.indexOf(":");
                    identifiers.add(str.substring(point + 1, str.length() - 1));

                }
            }

            return searchTitle(identifiers);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<String> searchTitle(ArrayList<String> identifiers) throws IOException {

        Scanner txtscan2 = new Scanner(new File(titlesPath));
        ArrayList<String> titles = new ArrayList<String>();

        while (txtscan2.hasNextLine()) {
            String str = txtscan2.nextLine();

            for (int i = 0; i < identifiers.size(); i++) {

                if (str.indexOf(identifiers.get(i)) != -1) {

                    point = str.indexOf(":");
                    point2 = str.indexOf("-");

                    titles.add(str.substring(point + 1, point2));
                }
            }
        }
        return titles;
    }

    public byte[] download() {
        try {

            Scanner txtscan = new Scanner(new File(titlesPath));
            ArrayList<String> identifiers = new ArrayList<String>();
            ArrayList<String> titles = new ArrayList<>();

            while (txtscan.hasNextLine()) {
                String str = txtscan.nextLine();
                if (str.indexOf(param) != -1) {

                    point = str.indexOf(":");
                    point2 = str.indexOf("-");
                    identifiers.add(str.substring(0, point));
                    titles.add(str.substring(point + 1, point2));

                }
            }

            File f = new File(filesPath);
            File[] matchingFiles = f.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.contains((identifiers.get(0)));
                }
            });

            return Files.readAllBytes(matchingFiles[0].toPath());


        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteContent() {

        ArrayList<String> identifiers = new ArrayList<String>();
        boolean found = false;


        try {

            File inputFile = new File(titlesPath);
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.indexOf(title) != -1 && currentLine.indexOf(client) != -1){

                    point = currentLine.indexOf(":");
                    identifiers.add(currentLine.substring(0,point));
                    found = true;
                    continue;
                }
                    writer.write(currentLine + System.getProperty("line.separator"));

            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);

            if (identifiers.size() >= 1){
                deleteTopics(identifiers);
                deleteFiles(identifiers);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return found;

    }

    public void deleteTopics(ArrayList<String> identifiers){

        try {

            File inputFile = new File(topicPath);
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                for (int i = 0; i < identifiers.size(); i++) {

                    if (currentLine.indexOf(identifiers.get(i)) != - 1) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));

                }
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFiles(ArrayList<String> identifiers){

        File index = new File(filesPath);
        String[]entries = index.list();
        for(String s: entries){
            File currentFile = new File(index.getPath(),s);
            for (int i = 0; i < identifiers.size(); i++){

                if (currentFile.getName().equals(identifiers.get(i))){

                    System.out.println(currentFile.getName());
                    currentFile.delete();
                }

            }

        }

    }

    public ArrayList<String> allTitles(){
        ArrayList<String> titles = new ArrayList<>();
        System.out.println("HELO");
        try {

            Scanner txtscan = new Scanner(new File(titlesPath));

            while (txtscan.hasNextLine()) {
                String str = txtscan.nextLine();

                    point = str.indexOf(":");
                    point2 = str.indexOf("-");
                    titles.add(str.substring(point + 1, point2));
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println(titles);
        return titles;
    }

    public ArrayList<String> showTitles(){

        ArrayList<String> identifiers = new ArrayList<String>();
        ArrayList<String> titles = new ArrayList<>();

        try {

            Scanner txtscan = new Scanner(new File(titlesPath));

            while (txtscan.hasNextLine()) {
                String str = txtscan.nextLine();
                if (str.indexOf(param) != -1) {

                    point = str.indexOf(":");
                    point2 = str.indexOf("-");
                    identifiers.add(str.substring(0, point));
                    titles.add(str.substring(point + 1, point2));

                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return titles;
    }
}

