package dev.progbloom.search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MaxSubarrayTests {
    static Stream<Arguments> provideMaxSubarrayAlgorithms() {
        return Stream.of(
                Arguments.of(new MaxSubarrayDivideAndConquer()),
                Arguments.of(new MaxSubarrayLinear())
        );
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenSingleElementArray_findMaxSubarray_shouldReturnSingleElementSubarray(AlgorithmMaxSubarray alg) {
        int[] arr = new int[]{5};
        MaxSubarrayResult properResult = new MaxSubarrayResult(0, 0, 5);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenLongArray_findMaxSubarray_shouldReturnProperSubarray(AlgorithmMaxSubarray alg) {
        int[] arr = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        MaxSubarrayResult properResult = new MaxSubarrayResult(7, 10, 43);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenLongArrayAndOddLength_findMaxSubarray_shouldReturnProperSubarray(AlgorithmMaxSubarray alg) {
        int[] arr = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7, 2};
        MaxSubarrayResult properResult = new MaxSubarrayResult(7, 10, 43);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenTwoMaxEqualAndPreviousByValueNumber_findMaxSubarray_shouldReturnMaxValue(AlgorithmMaxSubarray alg) {
        // this test checks comparing equal values. Two equal numbers here are: -3, -3, and prev by value: -4
        int[] arr = new int[]{-13, -3, -25, -20, -3, -16, -23, -18, -20, -7, -12, -5, -22, -15, -4, -7};
        MaxSubarrayResult properResult = new MaxSubarrayResult(1, 1, -3);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }

    @ParameterizedTest
    @MethodSource("provideMaxSubarrayAlgorithms")
    void givenOnlyNegativeNumbersArray_findMaxSubarray_shouldReturnMostLowerNegativeNumberAndMostLowerIndex(AlgorithmMaxSubarray alg) {
        int[] arr = new int[]{-13, -3, -25, -20, -3, -16, -23, -18, -20, -7, -12, -5, -22, -15, -3, -7};
        MaxSubarrayResult properResult = new MaxSubarrayResult(1, 1, -3);
        assertThat(alg.findMaxSubarray(arr, 0, arr.length - 1)).isEqualToComparingFieldByField(properResult);
    }
}
