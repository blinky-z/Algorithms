package me.progbloom.algo.search.maxsubarray;

/**
 * Find max sum subarray efficient algorithm
 * <p>
 * <b>Algorithmic paradigm:</b> Dynamic programming
 */
public class FindMaxSubarrayLinearAlgorithm implements FindMaxSubarrayAlgorithm {

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
     * @see FindMaxSubarrayResult
     */
    @Override
    public FindMaxSubarrayResult findMaxSubarray(int[] a, int low, int high) {
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

        return new FindMaxSubarrayResult(left, right, maxSum);
    }
}
