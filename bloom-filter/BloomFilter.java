import java.util.BitSet;

import strategy.*;

class BloomFilter {
    private BloomFilterConfig config;
    private BitSet bitArray;
    private HashStrategy hashStrategy;

    public BloomFilter(BloomFilterConfig config, HashStrategy hashStrategy) {
        this.config = config;
        this.bitArray = new BitSet(this.config.getBitArraySize());
        this.hashStrategy = hashStrategy;
    }

    public synchronized void add(String ele) {
        int bitArraySize = this.config.getBitArraySize();
        int k = this.config.getNumHashFunctions();

        if(ele == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        for(int seed=0; seed<k; seed++) {
            int pos = this.hashStrategy.hash(ele, seed, bitArraySize);
            bitArray.set(pos);
        }
    }

    public synchronized boolean mightContain(String ele) {
        int bitArraySize = this.config.getBitArraySize();
        int k = this.config.getNumHashFunctions();

        if(ele == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        for(int seed=0; seed<k; seed++) {
            int pos = this.hashStrategy.hash(ele, seed, bitArraySize);
            if(!bitArray.get(pos)) return false;
        }

        return true;
    }

    public synchronized void clear(){
        this.bitArray.clear();
    }

    public BloomFilterConfig getConfig() {
        return this.config;
    }

    // Builder
    static class Builder {
        private int expectedElements;
        private double falsePositiveRate = 0.01;
        private HashStrategy hashStrategy = new MurmurHashStrategy();

        public Builder expectedElements(int n) {
            this.expectedElements = n;
            return this;
        }

        public Builder falsePositiveRate(double rate) {
            this.falsePositiveRate = rate;
            return this;
        }

        public Builder hashStrategy(HashStrategy hash) {
            this.hashStrategy = hash;
            return this;
        }

        public BloomFilter build() {
            if (expectedElements <= 0) {
                throw new IllegalArgumentException("Expected elements must be positive");
            }
            if (falsePositiveRate <= 0 || falsePositiveRate >= 1) {
                throw new IllegalArgumentException(
                    "False positive rate must be between 0 and 1 (exclusive)");
            }

            BloomFilterConfig config = new BloomFilterConfig(expectedElements, falsePositiveRate);
            return new BloomFilter(config, hashStrategy);
        }
    }
}