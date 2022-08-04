package me.progbloom.algo.sort;

import static me.progbloom.common.ArrayUtils.swap;

/**
 * Сортировка выбором
 */
public class SelectionSort implements AlgorithmSort {

    @Override
    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
            }
            swap(a, i, minIdx);
        }
    }
}
