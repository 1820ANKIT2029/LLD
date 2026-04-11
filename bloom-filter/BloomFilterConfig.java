class BloomFilterConfig {
    private int expectedElements;
    private double falsePositiveRate;
    private int bitArraySize;
    private int numHashFunctions;

    public BloomFilterConfig(int expectedElements, double falsePositiveRate) {
        this.expectedElements = expectedElements;
        this.falsePositiveRate = falsePositiveRate;

        // m = -(n * ln(p)) / ln^2(2)
        this.bitArraySize = (int) Math.ceil(
            -((double)expectedElements * Math.log(falsePositiveRate)) / (Math.log(2) * Math.log(2))
        );

        // k = ln(2) * (m / n);
        this.numHashFunctions = Math.max(1, (int) Math.round(
            Math.log(2) * ((double) this.bitArraySize / (double) expectedElements)
        ));
    }

    public int getExpectedElements() {
        return this.expectedElements;
    }

    public double getFalsePositiveRate() {
        return this.falsePositiveRate;
    }

    public int getBitArraySize() {
        return this.bitArraySize;
    }

    public int getNumHashFunctions() {
        return this.numHashFunctions;
    }
}