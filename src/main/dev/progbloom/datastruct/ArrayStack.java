package dev.progbloom.datastruct;

import java.util.EmptyStackException;

/**
 * Stack implementation using an array.
 */
public class ArrayStack implements Stack<Integer> {
    private Integer[] a;

    private int last;

    private final int arraySize = 64;

    public ArrayStack() {
        a = new Integer[arraySize];
        last = -1;
    }

    @Override
    public synchronized Integer push(Integer item) {
        if (last + 1 == arraySize) {
            throw new StackOverflowException();
        }
        last++;
        a[last] = item;
        return item;
    }

    @Override
    public synchronized Integer pop() {
        if (last == -1) {
            throw new EmptyStackException();
        }
        Integer elem = a[last];
        a[last] = null;
        last--;
        return elem;
    }

    @Override
    public boolean isEmpty() {
        return last == -1;
    }
}
