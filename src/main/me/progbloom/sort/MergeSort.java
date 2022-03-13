package me.progbloom.sort;

/**
 * Сортировка слиянием (Merge sort)
 */
public class MergeSort implements AlgorithmSort<Integer> {

    /**
     * Объединяет два отсортированных отрезка a[left; mid], a[mid + 1; right].
     *
     * @param a     массив
     * @param left  начало первого отрезка
     * @param mid   разделитель между отрезками (конец первого отрезка)
     * @param right конец второго отрезка
     */
    private void merge(Integer[] a, final int left, final int mid, final int right) {
        final int n1 = mid - left + 1; // количество элементов в первом отрезке
        final int n2 = right - mid; // количество элементов во втором отрезке
        int i, j;

        // копируем отрезки в локальные массивы, потому что далее будем перезаписывать элементы в исходном массиве
        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];
        for (i = 0; i < n1; i++) {
            L[i] = a[left + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = a[mid + 1 + j];
        }

        // копируем элементы из L и R в порядке возрастания
        i = j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        // или в L, или в R еще остались элементы - их нужно просто скопировать
        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }

    /**
     * Сортирует отрезок
     *
     * @param a     массив
     * @param left  начало отрезка
     * @param right конец отрезка
     */
    private void mergeSort(Integer[] a, final int left, final int right) {
        if (left < right) {
            int q = (left + right) >> 1;
            mergeSort(a, left, q);
            mergeSort(a, q + 1, right);
            merge(a, left, q, right);
        }
    }

    /**
     * Sorts an array.
     *
     * @param a array to sort
     * @return sorted array
     */
    @Override
    public Integer[] sort(Integer[] a) {
        mergeSort(a, 0, a.length - 1);
        return a;
    }
}
