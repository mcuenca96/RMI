import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.file.Files;


public class Menu {


    public static void main(String[] args)  throws IOException {

        System.out.println("Welcome, what's your name?\n");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String name = null;
        name = bufferRead.readLine();

        System.out.println("What you want to do?\n");
        System.out.println("1-Upload content\n");
        System.out.println("2-Search content\n");
        System.out.println("3-Download content\n");
        System.out.println("4-Delete content\n");

        String s = null;
        s = bufferRead.readLine();


        ArrayList<String> theContent = new ArrayList<>();
        MediaClient cl = new MediaClient();

        if (s == null) {
            System.out.println("ERROR invalid operation");

        } else if (s.equals("1")) {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images & PNG & MP3", "jpg", "gif", "png", "mp3");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getName());
            }

            File file = chooser.getSelectedFile();


            if (file == null) {
                System.out.println("EXIT\n");
                System.exit(0);
            }
            System.out.println("What's the title\n");
            String title = bufferRead.readLine();
            System.out.print("What's the topic?\n");
            String topic = bufferRead.readLine();

            byte[] fileContent = Files.readAllBytes(file.toPath());
            cl.upload(fileContent, title, topic, name);
            System.out.println("Content stored correctly\n");
            System.exit(0);
        } else if (s.equals("2")) {
            System.out.println("Search content\n");
            System.out.println("1)Search by topic\n");
            System.out.println("2)Search by textual description\n");
            System.out.println("3)Show all the titles\n");
            String i = bufferRead.readLine();
            if (i.equals("1")) {
                System.out.println("What's the topic\n");
                String param = bufferRead.readLine();
                theContent = cl.searchContent(param);
                if (theContent.isEmpty()) {
                    System.out.println("There are no titles with this topic\n");
                    System.exit(0);
                }
                System.out.print(theContent);
            } else if (i.equals("2")) {
                System.out.println("What's the textual description\n");
                String param = bufferRead.readLine();
                System.out.println("Titles found:\n " + cl.getTitles(param));
            } else if (i.equals("3")){
                System.out.println(cl.allTitles());
            } else {
                    System.out.println("Not a valid operation\n");
                }

            } else if (s.equals("3")) {
                System.out.println("Download content, where you want to download it?\n");
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.showSaveDialog(null);
                String downloadPath = f.getSelectedFile() + "/";
                System.out.println(("What content would you like to download? (Write the title)\n"));

                String selectedTitle = bufferRead.readLine();
                if (cl.showTitles(selectedTitle)) {
                    System.out.println("There are more than one title with this name. Write the title exactly\n");
                    selectedTitle = bufferRead.readLine();
                    cl.download(selectedTitle, downloadPath);
                    System.out.println("Downloaded!");

                } else {
                    System.out.println(cl.getTitles(selectedTitle) + " will be downloaded, type yes if you are agree," +
                            "no otherwise\n");
                    String br = bufferRead.readLine();
                    if (br.equals("yes")){
                        cl.download(selectedTitle, downloadPath);
                        System.out.println("Downloaded!");
                    }
                    else if(br.equals("no")){
                        System.out.println("Exit\n");
                        System.exit(0);
                    } else {System.out.println("Not a valid option\n");}
                }


            } else if (s.equals("4")) {
                System.out.println("What title you want to delete?\n");
                String deleteTitle = bufferRead.readLine();
                if (cl.showTitles(deleteTitle)) {
                    System.out.println("There are more than one title with this name. Write the title exactly\n");
                    deleteTitle = bufferRead.readLine();
                    cl.delete(deleteTitle, name);
                }
                else {
                    System.out.println(cl.getTitles(deleteTitle) + " will be deleted, type yes if you are agree," +
                            "no otherwise\n");
                    String br = bufferRead.readLine();
                    if (br.equals("yes")){
                        cl.delete(deleteTitle,name);

                    }
                    else if(br.equals("no")){
                        System.out.println("Exit\n");
                        System.exit(0);
                    }
                    else {System.out.println("Not a valid option\n");}
                }





            } else {
                System.out.println("Not a valid option");
            }


        }

    }

