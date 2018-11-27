import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyStorage {

    private byte[] file;
    private String title;
    private String topic;
    private String id;



    public MyStorage(Content someContent)
    {
        this.file = someContent.getFile();
        this.title = someContent.getTitle();
        this.topic = someContent.getTopic();
        this.id = someContent.getId();

    }

    public void saveContent() {
        OutputStream os = null;

        try
        {

            os = new BufferedOutputStream(new FileOutputStream("/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/" + id));

            os.write(file);
            os.close();
            System.out.println("Content stored correctly!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void saveTitle()
    {

    }

    public void saveTopics()
    {

    }

}
