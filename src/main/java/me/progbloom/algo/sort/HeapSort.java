package me.progbloom.algo.sort;

import me.progbloom.collection.tree.BinaryHeap;

/**
 * Heapsort sorting algorithm.
 */
public class HeapSort implements AlgorithmSort {

    @Override
    public void sort(int[] a) {
        new BinaryHeap(a).heapsort();
    }
}
