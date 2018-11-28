import java.io.*;



public class MyStorage  {

    private byte[] file;
    private String title;
    private String topic;
    private int id;
    private String type;

    private String topicPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Topics/";
    private String titlesPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Titles/";
    private String filesPath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORAGE/Files/";
    private String typePath = "/home/marc/Documentos/ComputacioDistribuida/RMI/RMI_STORE/";



    public MyStorage(Content someContent)
    {
        this.file = someContent.getFile();
        this.title = someContent.getTitle();
        this.topic = someContent.getTopic();
        this.id = someContent.getId();

    }

    public MyStorage(String type)
    {
        this.type = type;
    }

    public void saveContent() {

        try (FileOutputStream fos = new FileOutputStream(filesPath + id)) {
            fos.write(file);
            //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
        }

        catch (IOException e){

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

    public String[] load()
    {

        String[] contents = {"X"};
        return contents;
    }

    }


