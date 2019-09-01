package dev.progbloom.datastruct;

import dev.progbloom.Utils;

/**
 * Binary heap data structure implementation.
 * <p>
 * To construct max binary heap, call function {@link #buildMaxHeap()} explicitly.
 * <p>
 * Link: {@code https://progbloom.dev/posts/25}
 */
public class BinaryHeap {
    private Integer[] a;

    private int heapSize;

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
            Utils.swap(a, i, largest);
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
            Utils.swap(a, 0, heapSize);
            heapSize--;
            maxHeapify(0);
        }
        return a;
    }

    public Integer[] getArray() {
        return a;
    }
}
