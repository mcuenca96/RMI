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

    public void download(String selectedTitle, String downloadPath) throws RemoteException{

        rmiConnection();
        byte[] downloaded = ourMedia.download(selectedTitle);

        // Type the path to your Desktop
        try (FileOutputStream fos = new FileOutputStream(downloadPath + selectedTitle)) {
            fos.write(downloaded);


        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public ArrayList<String> getTitles(String selectedTitle) throws RemoteException{
        rmiConnection();
        if (ourMedia.showTitles(selectedTitle).size() <= 0){
            System.out.println("No titles found\n");
            System.exit(0);
        }
        return ourMedia.showTitles(selectedTitle);
    }

    public ArrayList<String> allTitles() throws RemoteException{

        rmiConnection();
        return ourMedia.allTitles();
    }

    public boolean showTitles(String selectedTitle) throws RemoteException{

        rmiConnection();
        ArrayList<String> titles = ourMedia.showTitles(selectedTitle);

        if (titles.size() > 1) {

            for (int i = 0; i < titles.size(); i++) {

                System.out.println(titles.get(i));
            }

            return true;
        }

        return false;
    }

    public void delete(String deleteTitle, String client) throws RemoteException
    {
        rmiConnection();
        if (!ourMedia.delete(deleteTitle, client))
        {
            System.out.println("ERROR: There is no title with that name or you are not the one who uploaded it\n");
        }
        else {
            System.out.println(deleteTitle + " deleted");
        }
    }


}




