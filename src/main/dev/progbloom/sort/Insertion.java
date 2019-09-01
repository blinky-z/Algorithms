package dev.progbloom.sort;

import static dev.progbloom.Utils.swap;

/**
 * Insertion sort algorithm.
 * Link: {@code https://progbloom.dev/posts/19}
 */
public class Insertion implements AlgorithmSort<Integer> {
    /**
     * Sorts an array.
     * <p>
     * This realization moves elements to the right and then inserts the key into the sorted sequence.
     * It's slightly faster than swapping elements.
     *
     * @param a array to sort
     * @return sorted array
     */
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
     * Sorts an array.
     * <p>
     * This realization uses swapping to insert the key into the sorted sequence.
     *
     * @param a array to sort
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