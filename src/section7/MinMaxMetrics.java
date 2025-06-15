package section7;

public class MinMaxMetrics {

    // Add all necessary member variables
    private volatile long min;

    private volatile long max;



    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        // Add code here
        this.min = Long.MAX_VALUE;
        this.max = Long.MIN_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(long newSample) {
        // Add code here
        this.min = Math.min(this.min, newSample);
        this.max = Math.max(this.max, newSample);
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return this.min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return this.max;
    }
}
