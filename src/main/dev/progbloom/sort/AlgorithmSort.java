package dev.progbloom.sort;

/**
 * Generic interface for all the sorting algorithms.
 *
 * @param <T> input and result data type
 */
public interface AlgorithmSort<T extends Comparable> {

    /**
     * Sorts an array.
     *
     * This method should return the same array. If you need to allocate extra space, make sure to copy sorted array to the passed
     * one.
     *
     * @param a array to sort
     * @return sorted array
     */
    T[] sort(T[] a);
}
