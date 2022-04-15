package me.progbloom.datastruct.stack;

import java.util.EmptyStackException;

/**
 * Stack implementation using an array.
 */
public class ArrayStack<E> implements Stack<E> {

    private final E[] a;

    private int top;

    private final int arraySize = 64;

    @SuppressWarnings({"unchecked"})
    public ArrayStack() {
        a = (E[]) new Object[arraySize];
        top = -1;
    }

    @Override
    public synchronized E push(E item) {
        if (++top == arraySize) {
            throw new StackOverflowException();
        }
        a[top] = item;
        return item;
    }

    @Override
    public synchronized E pop() {
        if (top == -1) {
            throw new EmptyStackException();
        }
        E elem = a[top];
        a[top] = null;
        --top;
        return elem;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}
