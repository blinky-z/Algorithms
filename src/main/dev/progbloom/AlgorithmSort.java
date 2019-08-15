package dev.progbloom;

/**
 * Generic interface for the all algorithms.
 *
 * @param <T> input and result data type
 */
public interface AlgorithmSort<T extends Comparable> {
    T[] sort(T[] data);
}
