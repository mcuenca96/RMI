import javax.print.attribute.standard.Media;
import java.rmi.*;
import java.util.ArrayList;

public interface MediaInterface extends Remote {


    void upload(byte[] fileContent, String title, String topic, String client) throws RemoteException;
    byte[] download(String title) throws RemoteException;
    ArrayList<String> getContent(String param) throws RemoteException;
    boolean delete(String title, String client) throws RemoteException;








}