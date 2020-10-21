package dev.progbloom.datastruct;

import java.util.NoSuchElementException;

public interface Deque<E> {

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
    void addFirst(E e);

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
    void addLast(E e);

    /**
     * Retrieves and removes the first element of this deque.
     * This method throws an exception if this deque is empty.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E removeFirst();

    /**
     * Retrieves and removes the last element of this deque.
     * This method throws an exception if this deque is empty.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E removeLast();

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    boolean isEmpty();
}
