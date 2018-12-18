import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Random;


public class MediaImpl extends UnicastRemoteObject implements MediaInterface {
    public MediaImpl() throws RemoteException {
        super();

    }

    public int identifier;


    public void upload(byte[] fileContent, String title, String topic, String client) {


        Random rand = new Random();
        int n = rand.nextInt(99) + 1;
        identifier = fileContent.hashCode() + n;
        Content myContent = new Content(fileContent, title, topic, identifier,client);
        MyStorage store = new MyStorage(myContent);

        store.saveTitle();
        store.saveContent();
        store.saveTopics();
    }

    public ArrayList<String> allTitles(){
        MyStorage titles = new MyStorage();
        return titles.allTitles();
    }


   public ArrayList<String> getContent(String param) {

        MyStorage load = new MyStorage(param);

        if (load.load().size() == 0){ System.out.println("No topics found");}
        return load.load();

    }

    public byte[] download(String selectedTitle) {

        MyStorage download = new MyStorage(selectedTitle);
        return download.download();

    }

    public ArrayList<String> showTitles(String selectedTitle){

        MyStorage search = new MyStorage(selectedTitle);
        return search.showTitles();
    }

    public boolean delete(String deleteTitle, String client) {
        MyStorage delete = new MyStorage(deleteTitle, client);
        return delete.deleteContent();
    }



}

