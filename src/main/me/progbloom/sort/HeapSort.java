package me.progbloom.sort;

import me.progbloom.datastruct.tree.BinaryHeap;

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
