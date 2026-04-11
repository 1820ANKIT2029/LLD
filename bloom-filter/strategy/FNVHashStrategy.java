package strategy;

public class FNVHashStrategy implements HashStrategy {
    private static final int FNV_OFFSET_BASIS = 0x811c9dc5;
    private static final int FNV_PRIME = 0x01000193;

    @Override
    public int hash(String ele, int seed, int bitArraySize) {
        int hash = FNV_OFFSET_BASIS ^ seed;

        for (int i = 0; i < ele.length(); i++) {
            hash ^= ele.charAt(i);
            hash *= FNV_PRIME;
        }

        return Math.abs(hash % bitArraySize);
    }
}