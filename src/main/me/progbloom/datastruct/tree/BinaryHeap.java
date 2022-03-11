package me.progbloom.datastruct.tree;

import java.util.NoSuchElementException;

import static me.progbloom.Utils.swap;

/**
 * Имплементация двоичной кучи
 */
public class BinaryHeap {

    /**
     * Максимальный размер кучи.
     */
    private static final int MAX_HEAP_SIZE = 64;

    /**
     * Массив, содержащий кучу
     */
    private final Integer[] a;

    /**
     * Размер кучи. Также является указателем на последний элемент.
     */
    private int heapSize;

    /**
     * Конструктор пустой кучи
     */
    public BinaryHeap() {
        this.a = new Integer[MAX_HEAP_SIZE];
        heapSize = 0;
    }

    /**
     * Конструктор кучи на основе переданного массива
     *
     * @param a массив с данными
     */
    public BinaryHeap(Integer[] a) {
        this.a = a.clone();
        buildMaxHeap();
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

    /**
     * Добавляет элемент в кучу
     *
     * @param e элемент
     */
    public void add(Integer e) throws IllegalStateException {
        if (heapSize == a.length) {
            throw new IllegalStateException("Heap is full");
        }
        a[heapSize] = Integer.MIN_VALUE;
        increaseKey(heapSize, e);
        heapSize++;
    }

    /**
     * Увеличивает значение указанной ноды
     *
     * @param i      индекс ноды
     * @param newKey новый ключ, не меньший прежнего
     */
    private void increaseKey(int i, int newKey) throws IllegalStateException {
        if (newKey < a[i]) {
            throw new IllegalStateException("New key is lower than old key");
        }
        a[i] = newKey;
        upHeapify(i);
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

        // нужна проверка на сравнение с heapSize, т.к. мы могли бы получить индекс несуществующей ноды, если дальше элементов нет
        if (l <= heapSize && a[l] > a[largest]) {
            largest = l;
        }

        if (r <= heapSize && a[r] > a[largest]) {
            largest = r;
        }

        if (largest != i) {
            swap(a, i, largest);
            downHeapify(largest);
        }
    }

    /**
     * Строит кучу из неотсортированного массива
     */
    private void buildMaxHeap() {
        heapSize = a.length - 1;
        for (int i = a.length / 2; i >= 0; i--) {
            downHeapify(i);
        }
    }

    /**
     * Выполняет пирамидальную сортировку
     *
     * @return отсортированный массив
     */
    public Integer[] heapsort() {
        while (heapSize >= 1) {
            swap(a, 0, heapSize);
            heapSize--;
            downHeapify(0);
        }
        return a;
    }

    public Integer[] getArray() {
        return a;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }
}
