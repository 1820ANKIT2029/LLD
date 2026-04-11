package entities;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import tokenizer.Tokenizer;
import normalizer.Normalizer;

public class InvertedIndex {
    private Map<String, List<Posting>> index;
    private Tokenizer tokenizer;
    private Normalizer normalizer;

    public InvertedIndex(Tokenizer tokenizer, Normalizer normalizer) {
        this.index = new HashMap<>();
        this.tokenizer = tokenizer;
        this.normalizer = normalizer;
    }

    public List<Posting> getPostings(String term) {
        return this.index.getOrDefault(term, new ArrayList<>());
    }

    public void add(Document document) {
        List<String> tokens = this.tokenizer.tokenize(
            this.normalizer.normalize(document.getContext())
        );

        Map<String, Integer> mp = new HashMap<>();
        for(String str: tokens) {
            mp.put(str, mp.getOrDefault(str, 0) + 1);
        }

        for(Map.Entry<String, Integer> entry: mp.entrySet()) {
            List<Posting> postings = this.index
                .computeIfAbsent(entry.getKey(), k -> new ArrayList<>());

            postings.add(new Posting(document.getID(), entry.getValue()));

            postings.sort(Comparator.comparing(Posting::getDocumentID));
        }
    }
}