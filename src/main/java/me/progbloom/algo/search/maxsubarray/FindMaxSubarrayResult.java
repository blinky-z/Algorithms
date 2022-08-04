package me.progbloom.algo.search.maxsubarray;

public final class FindMaxSubarrayResult {

    public final int low;
    public final int high;
    public final int sum;

    public FindMaxSubarrayResult(int low, int high, int sum) {
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
