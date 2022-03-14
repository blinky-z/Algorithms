package me.progbloom.datastruct.tree;

import java.util.NoSuchElementException;

import static me.progbloom.Utils.swap;

/**
 * Имплементация двоичной кучи
 * <p>
 * Для двоичной кучи выполняется 2 условия:
 * 1. Свойство кучи: ключ в каждом узле больше или равен, чем любой из детей
 * 2. Свойство формы: бинарная куча - это полное или почти полное бинарное дерево (complete binary tree), то есть все уровни
 * дерева заполнены, кроме, возможно, последнего. Если последний уровень не полный, то узлы заполнены слева направо
 */
public class BinaryHeap {

    /**
     * Массив, содержащий кучу
     */
    private final int[] a;

    /**
     * Размер кучи
     * <p>
     * Индекс последнего элемента в массиве - это {@code heapSize - 1}.
     */
    private int heapSize;

    /**
     * Конструктор пустой кучи
     */
    public BinaryHeap() {
        this.a = new int[64];
        heapSize = 0;
    }

    /**
     * Строит кучу из неотсортированного массива (buildMaxHeap) за время O(n)
     *
     * @param a массив с данными
     */
    public BinaryHeap(int[] a) {
        this.a = a;
        this.heapSize = a.length;
        for (int i = a.length / 2; i >= 0; i--) {
            downHeapify(i);
        }
    }

    /**
     * Добавляет элемент в кучу
     *
     * @param e элемент
     */
    public void add(int e) throws IllegalStateException {
        if (heapSize == a.length) {
            throw new IllegalStateException("Heap is full");
        }
        a[heapSize - 1] = e;
        upHeapify(heapSize - 1);
        heapSize++;
    }

    /**
     * Извлекает максимальный элемент из кучи
     * <p>
     * Извлеченный элемент удаляется
     *
     * @return максимальный элемент
     */
    public int extractMax() throws NoSuchElementException {
        if (heapSize == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        int max = a[0];
        a[0] = a[heapSize - 1];
        heapSize--;
        downHeapify(0);
        return max;
    }

    /**
     * Поднимает элемент вверх по дереву, пока не будет восстановлено свойство кучи
     * <p>
     * Данная операция также известна как <i>sink-up</i>, <i>up-heapify</i>.
     *
     * @param i индекс ноды
     */
    private void upHeapify(int i) {
        while (i > 0 && a[parent(i)] < a[i]) {
            swap(a, parent(i), i);
            i = parent(i);
        }
    }

    /**
     * Опускает элемент вниз по дереву, пока не будет восстановлено свойство кучи
     * <p>
     * Операция предполагает, что для левого и правого поддерева уже выполняется свойство кучи.
     * Если текущий элемент уже больше элементов в левом или правом ребенке, то ничего делать не надо, то есть мы
     * восстановили свойство кучи.
     * <p>
     * Данная операция также известна как <i>max-heapify</i>.
     *
     * @param i индекс ноды
     */
    private void downHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int largest = i;

        // сравнение индекса с heapSize, т.к. мы могли получить индекс за пределом массива, если детей у ноды нет
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }

        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }

        if (largest != i) {
            swap(a, i, largest);
            downHeapify(largest);
        }
    }

    /**
     * Выполняет пирамидальную сортировку underlying массива
     * <p>
     * После отработки этого метода куча больше не подлежит использованию.
     */
    public void heapsort() {
        while (heapSize >= 1) {
            swap(a, 0, heapSize - 1);
            heapSize--;
            downHeapify(0);
        }
    }

    /**
     * Проверяет, пустая ли куча
     *
     * @return {@code true} если куча пустая, {@code false} иначе
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Возвращает индекс родителя для указанной ноды
     *
     * @param i индекс ноды
     * @return индекс родителя
     */
    private static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Возвращает индекс левого ребенка для указанной ноды
     *
     * @param i индекс ноды
     * @return индекс левого ребенка
     */
    private static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Возвращает индекс правого ребенка для указанной ноды
     *
     * @param i индекс ноды
     * @return индекс правого ребенка
     */
    private static int right(int i) {
        return 2 * i + 2;
    }
}
