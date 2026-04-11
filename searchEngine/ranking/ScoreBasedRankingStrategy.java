package ranking;

import java.util.List;
import entities.SearchResult;
import java.util.Comparator;

public class ScoreBasedRankingStrategy implements RankingStrategy {
    public void rank(List<SearchResult> results) {
        results.sort(Comparator.comparingDouble(SearchResult::getScore).reversed());
    }
}