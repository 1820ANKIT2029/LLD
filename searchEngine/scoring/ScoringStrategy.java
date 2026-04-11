package scoring;

import java.util.List;
import entities.SearchResult;
import entities.Posting;
import entities.Document;

public interface ScoringStrategy {
    public double calculateScore(String term, Posting posting, Document document);
}