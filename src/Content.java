public class Content
{

    private String title;
    private String topic;
    private String client;
    private byte[] file;
    private int id;

    public Content(byte[] file, String title, String topic, int id, String client){

        this.file = file;
        this.title = title;
        this.topic = topic;
        this.id = id;
        this.client = client;
    }

    public String getTitle(){

        return this.title;
    }

    public String getTopic(){

        return this.topic;
    }

    public byte[] getFile(){

        return this.file;
    }

    public int getId(){

        return this.id;
    }

    public String getClient(){

        return this.client;
    }
}
