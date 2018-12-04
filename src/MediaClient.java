import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;

import java.io.File;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class MediaClient {
    public MediaClient() {
    }

    private static int portNum = 7777;
    String URL = "rmi://localhost:" + portNum + "/some";
    MediaInterface ourMedia;


    public void rmiConnection() {

        try {

            ourMedia = (MediaInterface) Naming.lookup(URL);


        } catch (IOException e) {

            e.getStackTrace();

        } catch (Exception var5) {

            System.out.println("Exception in SomeClient: " + var5);

        }
    }

    public void upload(byte[] fileContent, String title, String topic, String client) throws RemoteException {

        rmiConnection();
        ourMedia.upload(fileContent, title, topic, client);

    }

    public ArrayList<String> searchContent (String param) throws RemoteException{

        rmiConnection();
        return ourMedia.getContent(param);

    }

    public void download(String selectedTitle) throws RemoteException{

        rmiConnection();
        byte[] downloaded = ourMedia.download(selectedTitle);

        // Type the path to your Desktop
        try (FileOutputStream fos = new FileOutputStream("/home/marc/Escritorio/" + selectedTitle)) {
            fos.write(downloaded);


        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void delete(String deleteTitle, String client) throws RemoteException
    {
        rmiConnection();
        ourMedia.delete(deleteTitle, client);
    }

    public void modify(String original, String newTitle, String client) throws RemoteException
    {
        rmiConnection();
        ourMedia.modify(original, newTitle, client);
    }

}




