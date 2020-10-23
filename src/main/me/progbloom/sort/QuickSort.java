package me.progbloom.sort;

import static me.progbloom.Utils.swap;

/**
 * Quicksort sorting algorithm.
 */
public class QuickSort implements AlgorithmSort<Integer> {

    private int partition_lomuto(Integer[] a, int p, int r) {
        int x = a[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (a[j] <= x) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    private void quicksort(Integer[] a, int p, int r) {
        if (p < r) {
            int q = partition_lomuto(a, p, r);
            quicksort(a, p, q - 1);
            quicksort(a, q + 1, r);
        }
    }

    /**
     * Sorts an array.
     *
     * @param a array to sort
     * @return sorted array
     */
    @Override
    public Integer[] sort(Integer[] a) {
        quicksort(a, 0, a.length - 1);
        return a;
    }
}
