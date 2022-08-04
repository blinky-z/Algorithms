package me.progbloom.algo.sort;

import static me.progbloom.common.ArrayUtils.maxInArray;

/**
 * Counting sort sorting algorithm.
 * <p>
 * Sort is stable.
 */
public class CountingSort implements AlgorithmSort {

    @Override
    public void sort(int[] a) {
        if (a.length < 2) {
            return;
        }

        int k = maxInArray(a);

        int[] c = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            c[i] = 0;
        }

        int elem;
        for (int j = 0; j < a.length; j++) {
            elem = a[j];
            c[elem] += 1;
        }

        for (int i = 1; i <= k; i++) {
            c[i] += c[i - 1];
        }

        int[] b = new int[a.length];
        for (int j = a.length - 1; j >= 0; j--) {
            elem = a[j];
            b[c[elem] - 1] = elem;
            c[elem]--;
        }

        System.arraycopy(b, 0, a, 0, a.length);
    }

    /**
     * Sorts array by digit.
     *
     * @param a     array to sort
     * @param digit which digit to sort by. 0 means the least significant digit
     */
    public void sortByDigit(int[] a, int digit) {
        int exp = (int) Math.pow(10, digit);

        int[] c = new int[10];
        for (int i = 0; i < 10; i++) {
            c[i] = 0;
        }

        int elem;
        for (int j = 0; j < a.length; j++) {
            elem = a[j];
            c[(elem / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            c[i] += c[i - 1];
        }

        int[] b = new int[a.length];
        for (int j = a.length - 1; j >= 0; j--) {
            elem = a[j];
            b[c[(elem / exp) % 10] - 1] = elem;
            c[(elem / exp) % 10]--;
        }

        System.arraycopy(b, 0, a, 0, a.length);
    }
}
