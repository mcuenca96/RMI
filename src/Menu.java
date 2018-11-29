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

        System.out.println("Welcome, what do you want to do?\n");
        System.out.println("1-Upload content\n");
        System.out.println("2-Search content\n");
        System.out.println("3-Download content\n");
        System.out.println("4-Delete content\n");

        String s = null;

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
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
            cl.upload(fileContent, title, topic);
            System.exit(0);
        }

        else if (  s.equals("2"))
        {
            System.out.println("Search content\n");


            String type = "Topics";
            System.out.println("What's the topic?\n");
            String param = bufferRead.readLine();
            theContent = cl.searchContent(param, type);

            System.out.print(theContent);

        }

        else if( s.equals("3"))
        {
            System.out.println("Download content");
            //TODO
        }
        else if ( s.equals("4"))
        {
            System.out.println("Delete content");
            //TODO
        }







    }
}


