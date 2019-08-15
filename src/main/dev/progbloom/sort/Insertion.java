package dev.progbloom.sort;

import dev.progbloom.AlgorithmSort;

import static dev.progbloom.sort.SortUtility.swap;

/**
 * Insertion sort algorithm.
 * Link: {@code https://progbloom.dev/posts/19}
 *
 * @author blinky
 */
public class Insertion implements AlgorithmSort<Integer> {
    @Override
    public Integer[] sort(Integer[] a) {
        int len = a.length;
        int j, key;

        for (int i = 1; i < len; i++) {
            key = a[i];
            for (j = i; j > 0 && key < a[j - 1]; j--) {
                a[j] = a[j - 1];
            }
            a[j] = key;
        }

        return a;
    }

    /**
     * Not such efficient as above realization.
     * <p>
     * This realization uses swapping, bot not moves elements to the right.
     *
     * @param a input array to sort
     * @return sorted array
     */
    public Integer[] sortWithSwapping(Integer[] a) {
        int len = a.length;
        int j;

        for (int i = 1; i < len; i++) {
            for (j = i; j > 0 && a[j] < a[j - 1]; j--) {
                swap(a, j, j - 1);
            }
        }

        return a;
    }
}