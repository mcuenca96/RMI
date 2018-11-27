import java.rmi.*;
import java.rmi.server.*;

public class MediaImpl extends UnicastRemoteObject implements MediaInterface {
    public MediaImpl() throws RemoteException {
        super();

    }


    public void upload(byte[] fileContent, String title, String topic) {


        String identifier = idGen();
        Content myContent = new Content(fileContent, title, topic, identifier);

        MyStorage store = new MyStorage(myContent);
        store.saveContent();
    }

    public String idGen()
    {
        String id = RandomString.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";


        return id;

    }


   /* public String[] getContent(String topic) {

        //TODO
    }

    public Byte[] download(String title) {

        //TODO

    } */
}

