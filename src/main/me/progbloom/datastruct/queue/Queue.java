package me.progbloom.datastruct.queue;

import java.util.NoSuchElementException;

public interface Queue<E> {

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
    E add(E e) throws IllegalStateException;

    /**
     * Retrieves and removes the head of this queue. this method throws an exception if
     * this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E remove();

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    boolean isEmpty();
}
