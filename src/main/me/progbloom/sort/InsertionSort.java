package me.progbloom.sort;

import static me.progbloom.Utils.swap;

/**
 * Сортировка вставками (Insertion Sort)
 */
public class InsertionSort implements AlgorithmSort<Integer> {

    @Override
    public Integer[] sort(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                swap(a, j, j - 1);
            }
        }
        return a;
    }
}