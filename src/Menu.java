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

        System.out.println("What you wanna do?\n");
        System.out.println("1-Upload content\n");
        System.out.println("2-Search content\n");
        System.out.println("3-Download content\n");
        System.out.println("4-Delete or modify content\n");

        String s = null;
        s = bufferRead.readLine();



        ArrayList<String> theContent;
        MediaClient cl = new MediaClient();

        if( s == null)
        {
            System.out.println("ERROR invalid operation");

        }

        else if( s.equals("1"))
        {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images & PNG & MP3", "jpg", "gif","png","mp3");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getName());
            }

            File file = chooser.getSelectedFile();


            if (file == null) System.exit(0);


            String title = chooser.getSelectedFile().getName();
            System.out.print("What's the topic?\n");
            String topic = bufferRead.readLine();

            byte[] fileContent = Files.readAllBytes(file.toPath());
            cl.upload(fileContent, title, topic, name);
            System.exit(0);
        }

        else if (  s.equals("2"))
        {
            System.out.println("Search content\n");



            System.out.println("What's the topic?\n");
            String param = bufferRead.readLine();
            theContent = cl.searchContent(param);

            if(theContent.isEmpty()){ System.out.println("There are no titles with this topic\n"); System.exit(0); }

            System.out.print(theContent);

        }

        else if( s.equals("3"))
        {
            System.out.println("Download content, the downloads will be stored in your desktop\n");
            System.out.println(("What content would you like to download? (Write the title)\n"));
            String selectedTitle = bufferRead.readLine();
            cl.download(selectedTitle);

        }
        else if ( s.equals("4"))
        {
            System.out.println("a) Delete content\n");
            System.out.println("b) Modify content\n");
            s = bufferRead.readLine();

            if(s.equals("a"))
            {
                System.out.println("What title you want to delete?\n");
                String deleteTitle = bufferRead.readLine();
                cl.delete(deleteTitle, name);
                System.out.println(deleteTitle + " deleted");
            }
            else if(s.equals("b"))
            {
                System.out.println("What title you want to modify?\n");
                String modifyTitle = bufferRead.readLine();
                System.out.println("Write the new title\n");
                String newTitle = bufferRead.readLine();
                cl.modify(modifyTitle, newTitle, name);
                System.out.println(modifyTitle + "  modified, the new title is " + newTitle);

            }
            }







    }
}


