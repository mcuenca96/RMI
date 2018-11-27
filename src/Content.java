public class Content
{

    private String title;
    private String topic;
    private byte[] file;
    private String id;

    public Content(byte[] file, String title, String topic, String id){

        this.file = file;
        this.title = title;
        this.topic = topic;
        this.id = id;

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

    public String getId(){

        return this.id;
    }
}
