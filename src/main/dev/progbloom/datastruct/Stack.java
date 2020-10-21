package dev.progbloom.datastruct;

import java.util.EmptyStackException;

public interface Stack<E> {

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param item the item to be pushed onto this stack.
     * @return the {@code item} argument.
     */
    E push(E item);

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack (the last item
     * of the {@code Vector} object).
     * @throws EmptyStackException if this stack is empty.
     */
    E pop();

    /**
     * Returns {@code true} if this stack contains no elements.
     *
     * @return {@code true} if this stack contains no elements
     */
    boolean isEmpty();
}
