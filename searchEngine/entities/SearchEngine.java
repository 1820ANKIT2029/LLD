package entities;

import java.util.List;
import java.util.ArrayList;
import ranking.RankingStrategy;
import scoring.ScoringStrategy;
import java.util.Comparator;
import tokenizer.WordTokenizer;
import normalizer.WordNormalizer;

public class SearchEngine {
    private static volatile SearchEngine instance;
    private static final Object lock = new Object();

    private DocumentStore documentStore;
    private InvertedIndex invertedIndex;
    private RankingStrategy rankingStrategy;
    private ScoringStrategy scoringStrategy;

    public SearchEngine() {
        this.documentStore = new DocumentStore();
        this.invertedIndex = new InvertedIndex(new WordTokenizer(), new WordNormalizer());
    }

    public static SearchEngine getInstance() {
        if(instance == null) {
            synchronized (lock)  {
                if(instance == null){
                    instance = new SearchEngine();
                }
            }
        }

        return instance;
    }

    public void setRankingStrategy(RankingStrategy rankingStrategy) {
        this.rankingStrategy = rankingStrategy;
    }

    public void setScoringStrategy(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }

    public void indexDocument(Document document) {
        this.documentStore.addDocument(document);
        this.invertedIndex.add(document);
    }

    public void indexDocuments(List<Document> documents) {
        for(Document document: documents) {
            this.indexDocument(document);
        }
    }

    private List<SearchResult> finalProcessing(String query, List<Posting> postings) {
        List<SearchResult> results = new ArrayList<>();
        for(Posting posting: postings) {
            Document doc = this.documentStore.getDocument(posting.getDocumentID());

            if(doc != null) {
                double score = this.scoringStrategy.calculateScore(query, posting, doc);

                results.add(new SearchResult(doc, score));
            }
        }

        this.rankingStrategy.rank(results);

        return results;
    }

    public List<SearchResult> search(String query) {
        query = query.toLowerCase();
        List<Posting> postings = this.invertedIndex.getPostings(query);

        return this.finalProcessing(query, postings);
    }

    public List<SearchResult> searchAll(List<String> queries) {
        if (queries == null || queries.isEmpty()) {
            return new ArrayList<>();
        }

        List<Posting> result = this.invertedIndex
                .getPostings(queries.get(0).toLowerCase());
        //result.sort(Comparator.comparing(Posting::getDocumentID));

        for (int i = 1; i < queries.size(); i++) {
            List<Posting> next = this.invertedIndex
                    .getPostings(queries.get(i).toLowerCase());

            //next.sort(Comparator.comparing(Posting::getDocumentID));
            result = intersect(result, next);
        }

        String s = "";
        for(String str: queries) s += str;

        return this.finalProcessing(s, result);
    }

    private List<Posting> intersect(List<Posting> l1, List<Posting> l2) {
        List<Posting> result = new ArrayList<>();

        int i = 0, j = 0;

        while (i < l1.size() && j < l2.size()) {
            String doc1 = l1.get(i).getDocumentID();
            String doc2 = l2.get(j).getDocumentID();

            if (doc1.equals(doc2)) {
                result.add(l1.get(i));
                i++;
                j++;
            } else if (doc1.compareTo(doc2) < 0) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }
}