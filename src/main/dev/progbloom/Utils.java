package dev.progbloom;

public final class Utils {
    public static void swap(Comparable<?>[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
