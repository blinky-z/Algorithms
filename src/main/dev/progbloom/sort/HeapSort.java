package dev.progbloom.sort;

import dev.progbloom.datastruct.BinaryHeap;

/**
 * Heapsort sorting algorithm.
 * Link: {@code https://progbloom.dev/posts/25}
 */
public class HeapSort implements AlgorithmSort<Integer> {
    /**
     * Sorts an array.
     *
     * @param a array to sort
     * @return sorted array
     */
    @Override
    public Integer[] sort(Integer[] a) {
        return new BinaryHeap(a).heapsort();
    }
}
