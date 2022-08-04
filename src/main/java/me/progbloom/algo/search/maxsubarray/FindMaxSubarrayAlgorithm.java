package me.progbloom.algo.search.maxsubarray;

/**
 * Generic interface for all the implementations of max sum subarray algorithm.
 */
public interface FindMaxSubarrayAlgorithm {

    /**
     * Finds max sum subarray.
     *
     * @param a    an subarray to perform search in
     * @param low  subarray start index
     * @param high subarray end index
     * @return start and end indexes of the max subarray, and sum of the elements
     * @see FindMaxSubarrayResult
     */
    FindMaxSubarrayResult findMaxSubarray(final int[] a, final int low, final int high);
}
