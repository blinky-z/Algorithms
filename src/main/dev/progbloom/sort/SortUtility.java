package dev.progbloom.sort;

final class SortUtility {
    static void swap(Comparable<?>[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
