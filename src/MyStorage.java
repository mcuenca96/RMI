import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class MyStorage {

    private byte[] file;
    private String title;
    private String topic;
    private int id;

    private String topicPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Topics/";
    private String titlesPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Titles/";



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

            os = new BufferedOutputStream(new FileOutputStream("/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Files/" + id));
            os.write(file);
            os.close();
            System.out.println("Content stored correctly!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void saveTitle() {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(titlesPath, true));
            writer.write(this.id + ":" + this.title);
            writer.newLine();

            writer.close();
            System.out.println("Title stored correctly!");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }



    public void saveTopics()
    {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(topicPath, true));
            writer.write(this.id + ":" + this.topic);
            writer.newLine();

            writer.close();
            System.out.println("Topic stored correctly!");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    }


