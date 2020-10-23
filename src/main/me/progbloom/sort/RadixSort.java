package me.progbloom.sort;

import static me.progbloom.Utils.maxInArray;

public class RadixSort implements AlgorithmSort<Integer> {

    private final CountingSort countingSort = new CountingSort();

    /**
     * Sorts an array.
     *
     * @param a array to sort
     * @return sorted array
     */
    @Override
    public Integer[] sort(Integer[] a) {
        if (a.length == 0) {
            return a;
        }
        int max = maxInArray(a);

        for (int digit = 0; max > 0; digit++) {
            max = max / 10;
            countingSort.sortByDigit(a, digit);
        }

        return a;
    }
}
