package entities;

import java.util.Map;
import java.util.HashMap;

public class DocumentStore {
    private Map<String, Document> store;

    public DocumentStore() {
        this.store = new HashMap<>();
    }

    public Document getDocument(String ID) {
        return this.store.get(ID);
    }

    public void addDocument(Document document) {
        this.store.put(document.getID(), document);
    }
}