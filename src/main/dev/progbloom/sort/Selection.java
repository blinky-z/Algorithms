package dev.progbloom.sort;

import static dev.progbloom.sort.SortUtility.swap;

/**
 * Selection sort algorithm.
 * Link: {@code https://progbloom.dev/posts/20}
 */
public class Selection implements AlgorithmSort<Integer> {
    /**
     * Sorts an array.
     *
     * @param a array to sort
     * @return sorted array
     */
    @Override
    public Integer[] sort(Integer[] a) {
        int len = a.length;
        int j, k;

        for (int i = 0; i < len; i++) {
            k = i;
            for (j = i + 1; j < len; j++) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            swap(a, i, k);
        }

        return a;
    }
}
