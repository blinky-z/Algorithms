package dev.progbloom.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SortTests {
    static Stream<Arguments> provideSortingAlgorithms() {
        return Stream.of(
                Arguments.of(new Insertion()),
                Arguments.of(new Selection()),
                Arguments.of(new Merge())
        );
    }

    @ParameterizedTest
    @MethodSource("provideSortingAlgorithms")
    void givenEmptyArray_sort_shouldReturnEmptyArray(AlgorithmSort<Integer> algorithmSort) {
        Integer[] data = new Integer[0];
        assertThat(algorithmSort.sort(data)).containsExactly(data);
    }

    @ParameterizedTest
    @MethodSource("provideSortingAlgorithms")
    void givenSingleElementArray_sort_shouldReturnSingleElementArray(AlgorithmSort<Integer> algorithmSort) {
        Integer[] data = new Integer[]{1};
        assertThat(algorithmSort.sort(data)).containsExactly(data);
    }

    @ParameterizedTest
    @MethodSource("provideSortingAlgorithms")
    void givenUnsortedArray_sort_shouldReturnSortedArray(AlgorithmSort<Integer> algorithmSort) {
        int tryCount = 10;
        int initDataLen = 100;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            Integer[] unsortedArray = TestUtils.generateRandomValuesArray(initDataLen);

            Integer[] sortedArray = Arrays.copyOf(unsortedArray, initDataLen);
            Arrays.sort(sortedArray);

            Integer[] returnedArray = algorithmSort.sort(unsortedArray);

            assertThat(returnedArray).containsExactly(sortedArray);
        }
    }

    @ParameterizedTest
    @MethodSource("provideSortingAlgorithms")
    void givenSortedArray_sort_shouldReturnSortedArray(AlgorithmSort<Integer> algorithmSort) {
        int tryCount = 10;
        int initDataLen = 100;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            Integer[] unsortedArray = TestUtils.generateRandomValuesArray(initDataLen);

            Integer[] sortedArray = Arrays.copyOf(unsortedArray, initDataLen);
            Arrays.sort(sortedArray);

            Integer[] returnedArray = algorithmSort.sort(sortedArray);

            assertThat(returnedArray).containsExactly(sortedArray);
        }
    }
}
