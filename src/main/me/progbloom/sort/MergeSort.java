package me.progbloom.sort;

/**
 * Merge sort algorithm.
 *
 * <b>Algorithmic paradigm:</b> Divide and Conquer.
 */
public class MergeSort implements AlgorithmSort<Integer> {

    private void merge(Integer[] a, final int p, final int q, final int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int i, j, k;
        Integer[] L = new Integer[n1 + 1];
        Integer[] R = new Integer[n2 + 1];

        for (i = 0; i < n1; i++) {
            L[i] = a[p + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = a[q + j + 1];
        }

        L[n1] = R[n2] = Integer.MAX_VALUE;
        i = j = 0;
        for (k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
        }
    }

    private Integer[] mergeSort(Integer[] a, final int p, final int r) {
        if (p < r) {
            int q = (p + r) >> 1;
            mergeSort(a, p, q);
            mergeSort(a, q + 1, r);
            merge(a, p, q, r);
        }
        return a;
    }

    /**
     * Sorts an array.
     *
     * @param a array to sort
     * @return sorted array
     */
    @Override
    public Integer[] sort(Integer[] a) {
        return mergeSort(a, 0, a.length - 1);
    }
}
