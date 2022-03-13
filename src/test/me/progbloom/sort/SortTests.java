package me.progbloom.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SortTests {

    static Stream<Arguments> provideSortingAlgorithms() {
        return Stream.of(
                Arguments.of(new BubbleSort()),
                Arguments.of(new SelectionSort()),
                Arguments.of(new InsertionSort()),
                Arguments.of(new QuickSort()),
                Arguments.of(new MergeSort()),
                Arguments.of(new TreeSort()),
                Arguments.of(new HeapSort()),
                Arguments.of(new CountingSort()),
                Arguments.of(new RadixSort())
        );
    }

    @ParameterizedTest
    @MethodSource("provideSortingAlgorithms")
    void givenEmptyArray_sort_shouldNotFail(AlgorithmSort algorithmSort) {
        int[] data = new int[0];
        algorithmSort.sort(data);
        assertThat(data).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideSortingAlgorithms")
    void givenSingleElementArray_sort_shouldNotFail(AlgorithmSort algorithmSort) {
        int[] data = {1};
        algorithmSort.sort(data);
        assertThat(data).containsExactly(1);
    }

    @ParameterizedTest
    @MethodSource("provideSortingAlgorithms")
    void givenUnsortedArray_sort_shouldReturnSortedArray(AlgorithmSort algorithmSort) {
        int tryCount = 10;
        int arrLength = 1000;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            int[] data = TestUtils.generateRandomValuesArray(arrLength, 1000);

            int[] expected = Arrays.copyOf(data, arrLength);
            Arrays.sort(expected);

            int[] actual = Arrays.copyOf(data, arrLength);
            algorithmSort.sort(actual);
            assertThat(actual).containsExactly(expected);
        }
    }

    @ParameterizedTest
    @MethodSource("provideSortingAlgorithms")
    void givenSortedArray_sort_shouldReturnSortedArray(AlgorithmSort algorithmSort) {
        int tryCount = 10;
        int arrLength = 100;
        for (int currentTry = 0; currentTry < tryCount; currentTry++) {
            int[] sorted = TestUtils.generateRandomValuesArray(arrLength, 1000);
            Arrays.sort(sorted);

            int[] actual = Arrays.copyOf(sorted, arrLength);
            algorithmSort.sort(actual);
            assertThat(actual).containsExactly(sorted);
        }
    }
}
