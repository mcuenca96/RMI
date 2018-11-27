import java.rmi.*;
import java.rmi.server.*;


public class MediaImpl extends UnicastRemoteObject implements MediaInterface {
    public MediaImpl() throws RemoteException {
        super();

    }

    public int identifier;


    public void upload(byte[] fileContent, String title, String topic) {

        RandomStringGenerator rnd = new RandomStringGenerator();
        //String identifier = rnd.generateString();
        Content myContent = new Content(fileContent, title, topic, identifier);

        MyStorage store = new MyStorage(myContent);
        store.saveContent();
        store.saveTitle();
        store.saveTopics();
    }



   /* public String[] getContent(String topic) {

        //TODO
    }

    public Byte[] download(String title) {

        //TODO

    } */
}

