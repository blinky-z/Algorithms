package me.progbloom.sort;

import me.progbloom.datastruct.tree.BinaryHeap;

/**
 * Heapsort sorting algorithm.
 */
public class HeapSort implements AlgorithmSort {

    @Override
    public void sort(int[] a) {
        new BinaryHeap(a).heapsort();
    }
}
