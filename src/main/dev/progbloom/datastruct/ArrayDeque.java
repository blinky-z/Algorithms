package dev.progbloom.datastruct;

import java.util.NoSuchElementException;

/**
 * Deque implementation using a circular buffer.
 * Link: {@code https://progbloom.dev/posts/27}
 */
public class ArrayDeque implements Deque<Integer> {
    private Integer[] q;

    private final int arraySize = 64;

    private int head;

    private int tail;

    public ArrayDeque() {
        q = new Integer[arraySize];
        head = 0;
        tail = 0;
    }

    /**
     * Inserts the specified element at the front of this deque if it is
     * possible to do so immediately without violating capacity restrictions,
     * throwing an {@code IllegalStateException} if no space is currently
     * available.
     *
     * @param e the element to add
     * @throws IllegalStateException if the element cannot be added at this
     *                               time due to capacity restrictions
     */
    @Override
    public void addFirst(Integer e) {
        if (((head == 0 && tail == arraySize - 1) || (tail + 1 == head))) {
            throw new IllegalStateException("Deque is full");
        }
        if (head == 0) {
            head = arraySize - 1;
        } else {
            head--;
        }
        q[head] = e;
    }

    /**
     * Inserts the specified element at the end of this deque if it is
     * possible to do so immediately without violating capacity restrictions,
     * throwing an {@code IllegalStateException} if no space is currently
     * available.
     *
     * @param e the element to add
     * @throws IllegalStateException if the element cannot be added at this
     *                               time due to capacity restrictions
     */
    @Override
    public void addLast(Integer e) {
        if (((head == 0 && tail == arraySize - 1) || (tail + 1 == head))) {
            throw new IllegalStateException("Deque is full");
        }
        q[tail] = e;
        if (tail == arraySize - 1) {
            tail = 0;
        } else {
            tail++;
        }
    }

    /**
     * Retrieves and removes the first element of this deque.
     * This method throws an exception if this deque is empty.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    public Integer removeFirst() {
        if (head == tail) {
            throw new NoSuchElementException("Deque is empty");
        }
        Integer elem = q[head];
        q[head] = null;
        if (head == arraySize - 1) {
            head = 0;
        } else {
            head++;
        }
        return elem;
    }

    /**
     * Retrieves and removes the last element of this deque.
     * This method throws an exception if this deque is empty.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    public Integer removeLast() {
        if (head == tail) {
            throw new NoSuchElementException("Deque is empty");
        }
        if (tail == 0) {
            tail = arraySize - 1;
        } else {
            tail--;
        }
        Integer elem = q[tail];
        q[tail] = null;
        return elem;
    }

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    @Override
    public boolean isEmpty() {
        return head == tail;
    }
}
