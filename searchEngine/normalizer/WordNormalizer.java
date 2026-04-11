package normalizer;

public class WordNormalizer implements Normalizer {
    public String normalize(String context) {
        return context.toLowerCase();
    }
}