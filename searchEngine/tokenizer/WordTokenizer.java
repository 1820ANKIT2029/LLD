package tokenizer;

import java.util.List;
import java.util.Arrays;

public class WordTokenizer implements Tokenizer {
    public List<String> tokenize(String context) {
        List<String> result = Arrays.stream(context.split("[., ]"))
                            .map(String::trim)
                            .filter(str -> !str.isEmpty())
                            .toList();
        return result;
    }
}