package me.progbloom.sort;

import static me.progbloom.Utils.maxInArray;

/**
 * Counting sort sorting algorithm.
 * <p>
 * Sort is stable.
 */
public class CountingSort implements AlgorithmSort<Integer> {

    /**
     * Sorts an array.
     *
     * @param a array to sort
     * @return a new sorted array
     */
    @Override
    public Integer[] sort(Integer[] a) {
        if (a.length == 0) {
            return a;
        }

        int k = maxInArray(a);

        Integer[] c = new Integer[k + 1];
        for (int i = 0; i <= k; i++) {
            c[i] = 0;
        }

        int elem;
        for (int j = 0; j < a.length; j++) {
            elem = a[j];
            c[elem] = c[elem] + 1;
        }

        for (int i = 1; i <= k; i++) {
            c[i] = c[i] + c[i - 1];
        }

        Integer[] b = new Integer[a.length];
        for (int j = a.length - 1; j >= 0; j--) {
            elem = a[j];
            b[c[elem] - 1] = elem;
            c[elem]--;
        }

        System.arraycopy(b, 0, a, 0, a.length);
        return a;
    }

    /**
     * Sorts array by digit.
     *
     * @param a     array to sort
     * @param digit which digit sort by. 0 means the least significant digit
     * @return a new sorted array
     */
    public Integer[] sortByDigit(Integer[] a, int digit) {
        int exp = (int) Math.pow(10, digit);

        Integer[] c = new Integer[10];
        for (int i = 0; i < 10; i++) {
            c[i] = 0;
        }

        int elem;
        for (int j = 0; j < a.length; j++) {
            elem = a[j];
            c[(elem / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            c[i] = c[i] + c[i - 1];
        }

        Integer[] b = new Integer[a.length];
        for (int j = a.length - 1; j >= 0; j--) {
            elem = a[j];
            b[c[(elem / exp) % 10] - 1] = elem;
            c[(elem / exp) % 10]--;
        }

        System.arraycopy(b, 0, a, 0, a.length);
        return a;
    }
}
