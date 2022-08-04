package me.progbloom.search;

import me.progbloom.algo.search.maxsubarray.FindMaxSubarrayAlgorithm;
import me.progbloom.algo.search.maxsubarray.FindMaxSubarrayDivideAndConquerAlgorithm;
import me.progbloom.algo.search.maxsubarray.FindMaxSubarrayLinearAlgorithm;
import me.progbloom.algo.search.maxsubarray.FindMaxSubarrayResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MaxSubarrayTests {
    static Stream<Arguments> provideMaxSubarrayAlgorithms() {
        return Stream.of(
                Arguments.of(new FindMaxSubarrayDivideAndConquerAlgorithm()),
                Arguments.of(new FindMaxSubarrayLinearAlgorithm())
        );
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenSingleElementArray_findMaxSubarray_shouldReturnSingleElementSubarray(FindMaxSubarrayAlgorithm alg) {
        int[] arr = new int[]{5};
        FindMaxSubarrayResult properResult = new FindMaxSubarrayResult(0, 0, 5);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenLongArray_findMaxSubarray_shouldReturnProperSubarray(FindMaxSubarrayAlgorithm alg) {
        int[] arr = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        FindMaxSubarrayResult properResult = new FindMaxSubarrayResult(7, 10, 43);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenLongArrayAndOddLength_findMaxSubarray_shouldReturnProperSubarray(FindMaxSubarrayAlgorithm alg) {
        int[] arr = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7, 2};
        FindMaxSubarrayResult properResult = new FindMaxSubarrayResult(7, 10, 43);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenTwoMaxEqualAndPreviousByValueNumber_findMaxSubarray_shouldReturnMaxValue(FindMaxSubarrayAlgorithm alg) {
        // this test checks comparing equal values. Two equal numbers here are: -3, -3, and prev by value: -4
        int[] arr = new int[]{-13, -3, -25, -20, -3, -16, -23, -18, -20, -7, -12, -5, -22, -15, -4, -7};
        FindMaxSubarrayResult properResult = new FindMaxSubarrayResult(1, 1, -3);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenOnlyNegativeNumbersArray_findMaxSubarray_shouldReturnMostLowerNegativeNumberAndMostLowerIndex(FindMaxSubarrayAlgorithm alg) {
        int[] arr = new int[]{-13, -3, -25, -20, -3, -16, -23, -18, -20, -7, -12, -5, -22, -15, -3, -7};
        FindMaxSubarrayResult properResult = new FindMaxSubarrayResult(1, 1, -3);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }
}
