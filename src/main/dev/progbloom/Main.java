package dev.progbloom;

import dev.progbloom.search.MaxSubarrayDivideAndConquer;
import dev.progbloom.search.MaxSubarrayResult;

public class Main {
    public static void main(String[] args) {
        MaxSubarrayDivideAndConquer maxSubarrayDivideAndConquer = new MaxSubarrayDivideAndConquer();
        MaxSubarrayResult res = maxSubarrayDivideAndConquer.findMaxSubarray(new int[]{-25, 20, -16, -23, 18, 20, -7, 12, -5, -22}, 0, 9);

        System.out.println("res = " + res);
    }
}
