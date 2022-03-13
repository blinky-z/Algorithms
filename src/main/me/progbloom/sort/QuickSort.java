package me.progbloom.sort;

import static me.progbloom.Utils.swap;

/**
 * Быстрая сортировка (Quick Sort)
 */
public class QuickSort implements AlgorithmSort {

    /**
     * Разбиение Ломуто: производит разбиение отрезка массива на два подотрезка: "меньшие или равные" и "большие"
     * опорного элемента
     * <p>
     * Опорным выбирается последний элемент отрезка.
     *
     * @param a массив
     * @param p индекс начала отрезка, включительно
     * @param r индекс конца отрезка, включительно
     * @return индекс опорного элемента
     */
    private int partitionLomuto(int[] a, int p, int r) {
        int pivot = a[r];
        int partitionIdx = p;
        for (int j = p; j < r; j++) {
            if (a[j] <= pivot) {
                swap(a, partitionIdx, j);
                partitionIdx++;
            }
        }
        // ставим опорный элемент на место
        swap(a, partitionIdx, r);
        return partitionIdx;
    }

    /**
     * Разбиение Хоара: производит разбиение отрезка массива на два подотрезка: "меньшие или равные" и "большие"
     * опорного элемента
     *
     * @param a массив
     * @param p индекс начала отрезка, включительно
     * @param r индекс конца отрезка, включительно
     * @return индекс опорного элемента
     */
    private int partitionHoare(int[] a, int p, int r) {
        int pivot = a[(p + r) / 2];
        int i = p;
        int j = r;
        while (true) {
            while (a[i] < pivot) {
                i++;
            }
            while (a[j] > pivot) {
                j--;
            }
            if (i >= j) {
                return j;
            }
            swap(a, i, j);
        }
    }

    /**
     * Производит быструю сортировку указанного отрезка массива (отрезок может быть самим массивом)
     *
     * @param a массив
     * @param p индекс начала отрезка, включительно
     * @param r индекс конца отрезка, включительно
     */
    private void quicksort(int[] a, int p, int r) {
        if (p < r) {
            int q = partitionLomuto(a, p, r);
            quicksort(a, p, q - 1);
            quicksort(a, q + 1, r);
        }
    }

    /**
     * Sorts an array.
     *
     * @param a array to sort
     * @return sorted array
     */
    @Override
    public void sort(int[] a) {
        quicksort(a, 0, a.length - 1);
    }
}
