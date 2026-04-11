package entities;

public class Posting {
    private String documentID;
    private int frequency;

    public Posting(String documentID, int frequency) {
        this.documentID = documentID;
        this.frequency = frequency;
    }

    public String getDocumentID() {
        return this.documentID;
    }

    public int getFrequency() {
        return this.frequency;
    }
}