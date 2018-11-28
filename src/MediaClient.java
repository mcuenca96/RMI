import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.Naming;

import java.io.File;
import java.nio.file.Files;
import java.rmi.RemoteException;


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

    public void upload(byte[] fileContent, String title, String topic) throws RemoteException {

        rmiConnection();
        ourMedia.upload(fileContent, title, topic);

    }

    public String[] searchContent (String param, String type) throws RemoteException{

        rmiConnection();
        return ourMedia.getContent(param, type);


    }



}
