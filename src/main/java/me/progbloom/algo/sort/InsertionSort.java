package me.progbloom.algo.sort;

import static me.progbloom.common.ArrayUtils.swap;

/**
 * Сортировка вставками (Insertion Sort)
 */
public class InsertionSort implements AlgorithmSort {

    @Override
    public void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                swap(a, j, j - 1);
            }
        }
    }
}