package dev.progbloom;


import dev.progbloom.sort.QuickSort;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextInt();
        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        QuickSort quickSort = new QuickSort();
        quickSort.sort(a);
    }
}
