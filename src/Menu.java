import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;


public class Menu {


    public static void main(String[] args)  throws RemoteException {

        System.out.println("Wellcome, what do you want to do?\n");
        System.out.println("1-Upload content\n");
        System.out.println("2-Search content\n");
        System.out.println("3-Download content\n");
        System.out.println("4-Delete content\n");

        String s = null;


        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            s = bufferRead.readLine();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        MediaClient cl = new MediaClient();

        if( s == null)
        {
            System.out.println("ERROR invalid operation");

        }

        else if( s.equals("1"))
        {

            System.out.println("Upload Content, select a file, title and topic\n");

            File file = new File(args[0]);
            String title = args[1];
            String topic = args[2];

            byte[] fileContent = new byte[(int) file.length()];

            cl.upload(fileContent, title, topic);


        }

        else if (  s.equals("2"))
        {
            System.out.println("Search content");

            // TODO
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


