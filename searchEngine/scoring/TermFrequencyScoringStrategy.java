package scoring;

import java.util.List;
import entities.SearchResult;
import entities.Posting;
import entities.Document;

public class TermFrequencyScoringStrategy implements ScoringStrategy {
    public double calculateScore(String term, Posting posting, Document document) {
        return posting.getFrequency();
    }
}