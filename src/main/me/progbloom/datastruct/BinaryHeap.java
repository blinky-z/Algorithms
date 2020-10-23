package me.progbloom.datastruct;

import java.util.NoSuchElementException;

import static me.progbloom.Utils.swap;

/**
 * Binary heap data structure implementation.
 * <p>
 * To construct max binary heap from passed array, call function {@link #buildMaxHeap()} explicitly.
 * Standard empty internal array size is 64.
 */
public class BinaryHeap {
    private Integer[] a;

    private int heapSize;

    public BinaryHeap() {
        this.a = new Integer[64];
        heapSize = 0;
    }

    public BinaryHeap(Integer[] a) {
        this.a = a;
        this.heapSize = 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    public void add(Integer e) throws IllegalStateException {
        if (heapSize == a.length) {
            throw new IllegalStateException("Heap is full");
        }
        a[heapSize] = Integer.MIN_VALUE;
        increaseKey(heapSize, e);
        heapSize++;
    }

    /**
     * Increases key of the node with index {@code i}.
     *
     * @param i      node
     * @param newKey new key such that newKey >= oldKey
     * @throws IllegalStateException if new key is lower that old key
     */
    public void increaseKey(int i, int newKey) throws IllegalStateException {
        if (newKey < a[i]) {
            throw new IllegalStateException("New key is lower than old key");
        }
        a[i] = newKey;
        while (i > 0 && a[parent(i)] < a[i]) {
            swap(a, parent(i), i);
            i = parent(i);
        }
    }

    public int extractMax() throws NoSuchElementException {
        if (heapSize == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        int max = a[0];
        a[0] = a[heapSize - 1];
        heapSize--;
        maxHeapify(0);
        return max;
    }

    /**
     * A recursive function to max heapify the given subtree. The root node of the given subtree is the node with index {@code i}, that is
     * the passed index.
     * This function assumes that left and right subtrees are already max heapified, so we only need to fix the root.
     *
     * @param i index of the subtree's root node
     */
    public void maxHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int largest;

        if (l <= heapSize && a[l] > a[i]) {
            largest = l;
        } else {
            largest = i;
        }

        if (r <= heapSize && a[r] > a[largest]) {
            largest = r;
        }

        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(largest);
        }
    }

    public void buildMaxHeap() {
        heapSize = a.length - 1;
        for (int i = a.length / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public Integer[] heapsort() {
        buildMaxHeap();
        while (heapSize >= 1) {
            swap(a, 0, heapSize);
            heapSize--;
            maxHeapify(0);
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
