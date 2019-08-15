package dev.progbloom;

import dev.progbloom.sort.Insertion;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Insertion insertion = new Insertion();
        System.out.println(Arrays.toString(insertion.sort(new Integer[]{6, 5, 3, 4, 1, 2})));
    }
}
