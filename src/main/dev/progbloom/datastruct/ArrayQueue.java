package dev.progbloom.datastruct;

import java.util.NoSuchElementException;

/**
 * Queue implementation using a circular buffer.
 * Link: {@code https://progbloom.dev/posts/27}
 */
public class ArrayQueue implements Queue<Integer> {
    private Integer[] q;

    private final int arraySize = 64;

    private int head;

    private int tail;

    public ArrayQueue() {
        q = new Integer[arraySize];
        head = 0;
        tail = 0;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     *
     * @param e the element to add
     * @return {@code true}
     */
    @Override
    public Integer add(Integer e) throws IllegalStateException {
        if ((tail + 1 == head) || (head == 0 && tail + 1 == arraySize)) {
            throw new IllegalStateException("Queue is full");
        }
        q[tail] = e;
        if (tail + 1 == arraySize) {
            tail = 0;
        } else {
            tail++;
        }
        return e;
    }

    /**
     * Retrieves and removes the head of this queue.
     * This method throws an exception if queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public Integer remove() throws NoSuchElementException {
        if (head == tail) {
            throw new NoSuchElementException("Queue is empty");
        }
        Integer e = q[head];
        q[head] = null;
        if (head + 1 == arraySize) {
            head = 0;
        } else {
            head++;
        }
        return e;
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
