package me.progbloom.datastruct.deque;

import java.util.NoSuchElementException;

/**
 * Deque implementation using a circular buffer.
 */
public class ArrayDeque implements Deque<Integer> {

    private final Integer[] arr;

    private final int arraySize = 64;

    private int head;

    private int tail;

    public ArrayDeque() {
        arr = new Integer[arraySize];
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
        int head = this.head;
        if (((head == arraySize - 1 && tail == 0) || (head + 1 == tail))) {
            throw new IllegalStateException("Deque is full");
        }
        arr[head] = e;
        this.head = nextHead(head);
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
        int tail = this.tail;
        if (((head == arraySize - 1 && tail == 0) || (head + 1 == tail))) {
            throw new IllegalStateException("Deque is full");
        }
        tail = nextTail(tail);
        arr[tail] = e;
        this.tail = tail;
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
        int head = this.head;
        if (head == tail) {
            throw new NoSuchElementException("Deque is empty");
        }
        head = prevHead(head);
        Integer elem = arr[head];
        arr[head] = null;
        this.head = head;
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
        int tail = this.tail;
        if (head == tail) {
            throw new NoSuchElementException("Deque is empty");
        }
        Integer elem = arr[tail];
        arr[tail] = null;
        this.tail = prevTail(tail);
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

    /*Utility Methods*/

    private int nextHead(int head) {
        if (head == arraySize - 1) {
            return 0;
        }
        return ++head;
    }

    private int prevHead(int head) {
        if (head == 0) {
            return arraySize - 1;
        }
        return --head;
    }

    private int nextTail(int tail) {
        if (tail == 0) {
            return arraySize - 1;
        }
        return --tail;
    }

    private int prevTail(int tail) {
        if (tail == arraySize - 1) {
            return 0;
        }
        return ++tail;
    }
}
