package dev.progbloom.sort;

import dev.progbloom.datastruct.BinaryHeap;

/**
 * Heapsort sorting algorithm.
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
