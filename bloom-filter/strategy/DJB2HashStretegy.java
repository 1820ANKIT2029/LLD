package strategy;

public class DJB2HashStrategy implements HashStrategy {
    @Override
    public int hash(String ele, int seed, int bitArraySize) {
        int hash = 5381 + seed;

        for (int i = 0; i < ele.length(); i++) {
            hash = ((hash << 5) + hash) + ele.charAt(i); // hash * 33 + c
        }

        return Math.abs(hash % bitArraySize);
    }
}