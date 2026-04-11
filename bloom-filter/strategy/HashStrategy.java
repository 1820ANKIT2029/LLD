package strategy;

public interface HashStrategy {
    int hash(String ele, int seed, int bitArraySize);
}