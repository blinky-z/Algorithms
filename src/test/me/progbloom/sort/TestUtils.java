package me.progbloom.sort;

import java.util.Random;

public abstract class TestUtils {
    private static final Random rand = new Random();

    /**
     * Generates array filled with random values.
     *
     * @param len        array len
     * @param upperBound upper bound of generated numbers, exclusively
     * @return generated random array
     */
    public static Integer[] generateRandomValuesArray(int len, int upperBound) {
        Integer[] a = new Integer[len];
        for (int i = 0; i < len; i++) {
            a[i] = rand.nextInt(upperBound);
        }
        return a;
    }
}
