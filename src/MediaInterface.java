import javax.print.attribute.standard.Media;
import java.rmi.*;
import java.util.ArrayList;

public interface MediaInterface extends Remote {


    void upload(byte[] fileContent, String title, String topic) throws RemoteException;

    //Byte[] download(String title) throws RemoteException;
    ArrayList<String> getContent(String param, String type) throws RemoteException;
    //void deleteContent(String title);







}