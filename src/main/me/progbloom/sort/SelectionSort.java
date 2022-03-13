package me.progbloom.sort;

import static me.progbloom.Utils.swap;

/**
 * Сортировка выбором
 */
public class SelectionSort implements AlgorithmSort<Integer> {

    @Override
    public Integer[] sort(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
            }
            swap(a, i, minIdx);
        }
        return a;
    }
}
