import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;


public class MediaImpl extends UnicastRemoteObject implements MediaInterface {
    public MediaImpl() throws RemoteException {
        super();

    }

    public int identifier;


    public void upload(byte[] fileContent, String title, String topic) {


        Content myContent = new Content(fileContent, title, topic, identifier);
        MyStorage store = new MyStorage(myContent);

        store.saveContent();
        store.saveTitle();
        store.saveTopics();
    }



   public ArrayList<String> getContent(String param, String type) {

        MyStorage load = new MyStorage(param, type);

        if (load.load().size() == 0){ System.out.println("No topics found");}
        return load.load();

    }

/*    public Byte[] download(String title) {

        //TODO

    }
    */
}

