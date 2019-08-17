package dev.progbloom;

import dev.progbloom.sort.Merge;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Merge merge = new Merge();
        System.out.println(Arrays.toString(merge.sort(new Integer[]{6, 5, 1, 3, 4, 2})));
    }
}
