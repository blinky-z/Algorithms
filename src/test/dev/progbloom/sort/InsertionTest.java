package dev.progbloom.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class InsertionTest {
    private Insertion insertion = new Insertion();

    @Test
    void givenEmptyArray_sort_shouldReturnEmptyArray() {
        Integer[] data = new Integer[0];
        assertThat(insertion.sort(data)).containsExactly(data);
    }

    @Test
    void givenSingleElementArray_sort_shouldReturnSingleElementArray() {
        Integer[] data = new Integer[]{1};
        assertThat(insertion.sort(data)).containsExactly(data);
    }

    @Test
    void givenUnsortedArray_sort_shouldReturnSortedArray() {
        int tryCount = 10;
        int initDataLen = 100;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            Integer[] unsortedArray = TestUtils.generateRandomValuesArray(initDataLen);

            Integer[] sortedArray = Arrays.copyOf(unsortedArray, initDataLen);
            Arrays.sort(sortedArray);

            Integer[] returnedArray = insertion.sort(unsortedArray);

            assertThat(returnedArray).containsExactly(sortedArray);
        }
    }

    @Test
    void givenSortedArray_sort_shouldReturnSortedArray() {
        int tryCount = 10;
        int initDataLen = 100;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            Integer[] unsortedArray = TestUtils.generateRandomValuesArray(initDataLen);

            Integer[] sortedArray = Arrays.copyOf(unsortedArray, initDataLen);
            Arrays.sort(sortedArray);

            Integer[] returnedArray = insertion.sort(sortedArray);

            assertThat(returnedArray).containsExactly(sortedArray);
        }
    }

    @Test
    void givenEmptyArray_sortWithSwapping_shouldReturnEmptyArray() {
        Integer[] data = new Integer[0];
        assertThat(insertion.sortWithSwapping(data)).containsExactly(data);
    }

    @Test
    void givenSingleElementArray_sortWithSwapping_shouldReturnSingleElementArray() {
        Integer[] data = new Integer[]{1};
        assertThat(insertion.sortWithSwapping(data)).containsExactly(data);
    }

    @Test
    void givenUnsortedArray_sortWithSwapping_shouldReturnSortedArray() {
        int tryCount = 10;
        int initDataLen = 100;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            Integer[] unsortedArray = TestUtils.generateRandomValuesArray(initDataLen);

            Integer[] sortedArray = Arrays.copyOf(unsortedArray, initDataLen);
            Arrays.sort(sortedArray);

            Integer[] returnedArray = insertion.sortWithSwapping(unsortedArray);

            assertThat(returnedArray).containsExactly(sortedArray);
        }
    }

    @Test
    void givenSortedArray_sortWithSwapping_shouldReturnSortedArray() {
        int tryCount = 10;
        int initDataLen = 100;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            Integer[] unsortedArray = TestUtils.generateRandomValuesArray(initDataLen);

            Integer[] sortedArray = Arrays.copyOf(unsortedArray, initDataLen);
            Arrays.sort(sortedArray);

            Integer[] returnedArray = insertion.sortWithSwapping(sortedArray);

            assertThat(returnedArray).containsExactly(sortedArray);
        }
    }
}