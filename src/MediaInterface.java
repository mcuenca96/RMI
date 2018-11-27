import javax.print.attribute.standard.Media;
import java.rmi.*;

public interface MediaInterface extends Remote {


    void upload(byte[] fileContent, String title, String topic) throws RemoteException;

    //Byte[] download(String title) throws RemoteException;
    //String[] getContent(String topic) throws RemoteException;
    //void deleteContent(String title);







}