package strategy;

public class MurmurHashStrategy implements HashStrategy {
    public int hash(String ele, int seed, int bitArraySize) {
        byte[] data = ele.getBytes();
        int h = seed;

        for (byte b : data) {
            h ^= b;
            h *= 0x5bd1e995;
            h ^= (h >>> 13);
        }

        h ^= (h >>> 16);
        h *= 0x85ebca6b;
        h ^= (h >>> 13);

        return Math.abs(h % bitArraySize);
    }
}