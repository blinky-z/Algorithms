package me.progbloom;

public final class Utils {

    private Utils() {
    }

    public static void swap(Comparable<?>[] a, int i, int j) {
        Comparable<?> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Returns value of the max element in not empty array.
     *
     * @param a a not empty array
     * @return value of max element
     */
    public static <T extends Comparable<? super T>> T maxInArray(T[] a) {
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(max) > 0) {
                max = a[i];
            }
        }
        return max;
    }
}
