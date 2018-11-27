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


    public void upload(byte[] fileContent, String title, String topic) throws RemoteException {

        try {

            String URL = "rmi://localhost:" + portNum + "/some";
            MediaInterface ourMedia = (MediaInterface) Naming.lookup(URL);
            ourMedia.upload(fileContent, title, topic);


        } catch (IOException e) {

            e.getStackTrace();

        } catch (Exception var5) {

            System.out.println("Exception in SomeClient: " + var5);

        }

    }

    public String[] searchContent (String param){

        String[] results = {"x"};

        return results;
    }

}
