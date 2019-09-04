package dev.progbloom;

public final class Utils {
    private Utils() {
    }

    public static void swap(Comparable<?>[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Returns value of the max element in not empty array.
     *
     * @param a a not empty array
     * @return value of max element
     */
    public static int maxInArray(Integer[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }
}
