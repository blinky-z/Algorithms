package me.progbloom.sort;

import static me.progbloom.Utils.maxInArray;

public class RadixSort implements AlgorithmSort {

    private final CountingSort countingSort = new CountingSort();

    @Override
    public void sort(int[] a) {
        if (a.length < 2) {
            return;
        }

        int max = maxInArray(a);

        for (int digit = 0; max > 0; digit++) {
            max = max / 10;
            countingSort.sortByDigit(a, digit);
        }
    }
}
