package dev.progbloom.search;

/**
 * Find max sum subarray efficient algorithm.
 */
public class MaxSubarrayLinear implements AlgorithmMaxSubarray {

    /**
     * Finds max sum subarray.
     * <p>
     * Handles all negative numbers case.
     * Handles two or more equal max sums case - returns the subarray starting with the most lower index.
     *
     * @param a    an subarray to perform search in
     * @param low  subarray start index
     * @param high subarray end index
     * @return start and end indexes of the max subarray, and sum of the elements
     * @see MaxSubarrayResult
     */
    @Override
    public MaxSubarrayResult findMaxSubarray(int[] a, int low, int high) {
        int sum;
        int maxSum = Integer.MIN_VALUE;
        int curLeft;
        int left, right;

        left = right = 0;

        curLeft = 0;
        sum = 0;
        for (int i = low; i <= high; i++) {
            sum += a[i];
            if (sum > maxSum) {
                maxSum = sum;
                left = curLeft;
                right = i;
            }
            if (sum < 0) {
                sum = 0;
                curLeft = i + 1;
            }
        }

        return new MaxSubarrayResult(left, right, maxSum);
    }
}
