import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.FileDialog;
import java.awt.Frame;


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

        String[] theContent;
        MediaClient cl = new MediaClient();

        if( s == null)
        {
            System.out.println("ERROR invalid operation");

        }

        else if( s.equals("1"))
        {

            FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getFile();
            if (file == null) System.exit(0);
            System.out.println(file + " chosen.");

            String title = file;
            System.out.print("What's the topic?\n");
            String topic = bufferRead.readLine();

            byte[] fileContent = new byte[file.length()];
            cl.upload(fileContent, title, topic);
            System.exit(0);
        }

        else if (  s.equals("2"))
        {
            System.out.println("Search content\n");

            System.out.println("a)Search by title\n");
            System.out.println("b)Search by topic\n");

            String resp = bufferRead.readLine();

            if( resp.equals("a"))
            {
                String type = "Titles";
                System.out.println("What's the title?\n");
                String param = bufferRead.readLine();
                theContent = cl.searchContent(param, type);

                System.out.print(theContent);
            }

            else if (resp.equals("b"))
            {
                String type = "Topics";
                System.out.println("What's the topic?\n");
                String param = bufferRead.readLine();
                theContent = cl.searchContent(param, type);

                System.out.print(theContent);

            }
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


