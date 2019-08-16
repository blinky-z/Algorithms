package dev.progbloom;

/**
 * Generic interface for the all sorting algorithms.
 *
 * @param <T> input and result data type
 */
public interface AlgorithmSort<T extends Comparable> {
    /**
     * Sorts an array.
     *
     * @param a array to sort
     * @return sorted array
     */
    T[] sort(T[] a);
}
