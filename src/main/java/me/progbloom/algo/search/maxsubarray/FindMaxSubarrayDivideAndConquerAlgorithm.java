package me.progbloom.algo.search.maxsubarray;

/**
 * Find max sum subarray algorithm
 * <p>
 * <b>Algorithmic paradigm:</b> Divide and Conquer
 */
public class FindMaxSubarrayDivideAndConquerAlgorithm implements FindMaxSubarrayAlgorithm {

    private FindMaxSubarrayResult findMaxCrossingSubarray(final int[] a, final int low, final int mid, final int high) {
        int sum;
        int left, right;
        int leftSum, rightSum;
        int i;

        left = right = -1; // убираем ругательства компилятора
        leftSum = rightSum = Integer.MIN_VALUE;

        sum = 0;
        for (i = mid; i >= low; i--) {
            sum += a[i];
            if (sum > leftSum) {
                leftSum = sum;
                left = i;
            }
        }

        sum = 0;
        for (i = mid + 1; i <= high; i++) {
            sum += a[i];
            if (sum > rightSum) {
                rightSum = sum;
                right = i;
            }
        }

        return new FindMaxSubarrayResult(left, right, leftSum + rightSum);
    }

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
    public FindMaxSubarrayResult findMaxSubarray(final int[] a, final int low, final int high) {
        if (low == high) {
            return new FindMaxSubarrayResult(low, high, a[low]);
        }
        int mid = (low + high) >> 1;
        FindMaxSubarrayResult leftResult = findMaxSubarray(a, low, mid);
        FindMaxSubarrayResult rightResult = findMaxSubarray(a, mid + 1, high);
        FindMaxSubarrayResult crossingResult = findMaxCrossingSubarray(a, low, mid, high);

        if (leftResult.sum >= rightResult.sum && leftResult.sum >= crossingResult.sum) {
            return leftResult;
        } else if (rightResult.sum > leftResult.sum && rightResult.sum > crossingResult.sum) {
            return rightResult;
        } else {
            return crossingResult;
        }
    }
}
