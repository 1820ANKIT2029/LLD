package entities;

public class Document {
    private String ID;
    private String title;
    private String context;

    public Document(String ID, String title, String context) {
        this.ID = ID;
        this.title = title;
        this.context = context;
    }

    public String getID() {return this.ID;}
    public String getTitle() {return this.title;}
    public String getContext() {return this.context;}
}