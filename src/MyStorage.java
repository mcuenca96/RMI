import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class MyStorage {

    private byte[] file;
    private String title;
    private String topic;
    private int id;
    private String type;
    private int contentStored;
    private String param;

    private String topicPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Topics/";
    private String titlesPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Titles/";
    private String filesPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Files/";
    private String typePath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORE/";


    public MyStorage(Content someContent) {
        this.file = someContent.getFile();
        this.title = someContent.getTitle();
        this.topic = someContent.getTopic();
        this.id = someContent.getId();

    }

    public MyStorage(String param, String type) {
        this.type = type;
        this.param = param;
    }

    public void saveContent() {

        try (FileOutputStream fos = new FileOutputStream(filesPath + id)) {
            fos.write(file);
            contentStored += 1;

        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    public void saveTitle() {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(titlesPath, true));
            writer.write(this.id + ":" + this.title);
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
        ArrayList<String> titles = new ArrayList<String>();

        try {

            Scanner txtscan = new Scanner(new File(topicPath));

            int point;

            while (txtscan.hasNextLine()) {
                String str = txtscan.nextLine();
                if (str.indexOf(param) != -1) {

                    point = str.indexOf(":");
                    identifiers.add(str.substring(point + 1, str.length() - 1));

                }
            }
            Scanner txtscan2 = new Scanner(new File(titlesPath));

            while (txtscan2.hasNextLine()) {
                String str = txtscan2.nextLine();

                for( int i = 0; i < identifiers.size(); i++) {

                    if (str.indexOf(identifiers.get(i)) != -1) {

                        point = str.indexOf(":");
                        titles.add(str.substring(point + 1));
                    }
                }
            }


        } catch (IOException e) {

            e.printStackTrace();
        }


        return titles;
    }
}

