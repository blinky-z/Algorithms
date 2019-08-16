package dev.progbloom.sort;

import java.util.Random;

public abstract class TestUtils {
    private static final Random rand = new Random();

    public static Integer[] generateRandomValuesArray(int len) {
        Integer[] a = new Integer[len];
        for (int i = 0; i < len; i++) {
            a[i] = rand.nextInt();
        }
        return a;
    }
}
