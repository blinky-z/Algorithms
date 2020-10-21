package dev.progbloom.search;

public final class MaxSubarrayResult {

    public final int low;
    public final int high;
    public final int sum;

    public MaxSubarrayResult(int low, int high, int sum) {
        this.low = low;
        this.high = high;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "MaxSubarrayResult{" +
                "low=" + low +
                ", high=" + high +
                ", sum=" + sum +
                '}';
    }
}
