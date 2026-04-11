package ranking;

import java.util.List;
import entities.SearchResult;

public interface RankingStrategy {
    public void rank(List<SearchResult> results);
}