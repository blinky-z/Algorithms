package me.progbloom.datastruct;

import java.util.NoSuchElementException;

/**
 * Priority queue implementation.
 * <p>
 * Each element is just a key, so element's priority is equal to the key.
 * Queue supports adding up to 64 elements.
 */
public class PriorityQueue implements Queue<Integer> {

    private BinaryHeap heap;

    private static final int queueSize = 64;

    public PriorityQueue() {
        this.heap = new BinaryHeap(new Integer[queueSize]);
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * the passed element on success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     *
     * @param e the element to add
     * @return the {@code e} argument.
     * @throws IllegalStateException if the element cannot be added at this
     *                               time due to capacity restrictions
     */
    @Override
    public Integer add(Integer e) throws IllegalStateException {
        try {
            heap.add(e);
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("Queue is full");
        }
        return e;
    }

    /**
     * Retrieves and removes the element with max priority. this method throws an exception if
     * this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public Integer remove() throws NoSuchElementException {
        try {
            return heap.extractMax();
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("Queue is empty");
        }
    }

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
