package entities;

public class SearchResult {
    private Document document;
    private double score;

    public SearchResult(Document document, double score) {
        this.document = document;
        this.score = score;
    }

    public Document getDocument() {return this.document;}
    public double getScore() {return this.score;}

    public String toString() {
        return this.document.getID()+"#"+this.document.getTitle();
    }
}