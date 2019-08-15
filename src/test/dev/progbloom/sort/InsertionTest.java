package dev.progbloom.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class InsertionTest {
    private Random rand = new Random();

    @Test
    void givenEmptyArray_sort_shouldReturnEmptyArray() {
        Insertion insertion = new Insertion();
        Integer[] data = new Integer[0];
        assertThat(insertion.sort(data)).containsExactly(data);
    }

    @Test
    void givenSingleElementArray_sort_shouldReturnSingleElementArray() {
        Insertion insertion = new Insertion();
        Integer[] data = new Integer[]{1};
        assertThat(insertion.sort(data)).containsExactly(data);
    }

    @Test
    void givenUnsortedArray_sort_shouldReturnSortedArray() {
        int tryCount = 10;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            int initDataLen = 100;

            Insertion insertion = new Insertion();
            Integer[] unsortedArray = new Integer[initDataLen];
            for (int i = 0; i < initDataLen; i++) {
                unsortedArray[i] = rand.nextInt();
            }
            Integer[] sortedArray = Arrays.copyOf(unsortedArray, initDataLen);
            Arrays.sort(sortedArray);

            Integer[] returnedArray = insertion.sort(unsortedArray);

            assertThat(returnedArray).hasSize(initDataLen);
            assertThat(returnedArray).containsExactly(sortedArray);
        }
    }

    @Test
    void givenSortedArray_sort_shouldReturnSortedArray() {
        int tryCount = 10;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            int initDataLen = 100;

            Insertion insertion = new Insertion();
            Integer[] unsortedArray = new Integer[initDataLen];
            for (int i = 0; i < initDataLen; i++) {
                unsortedArray[i] = rand.nextInt();
            }
            Integer[] sortedArray = Arrays.copyOf(unsortedArray, initDataLen);
            Arrays.sort(sortedArray);

            Integer[] returnedArray = insertion.sort(sortedArray);

            assertThat(returnedArray).hasSize(initDataLen);
            assertThat(returnedArray).containsExactly(sortedArray);
        }
    }
}