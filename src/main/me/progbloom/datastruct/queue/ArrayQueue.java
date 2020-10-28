package me.progbloom.datastruct.queue;

import java.util.NoSuchElementException;

/**
 * Имплементация ограниченной по размеру очереди с помощью кольцевого буфера.
 */
public class ArrayQueue<E> implements Queue<E> {

    private final E[] q;

    private final int arraySize = 64;

    /**
     * Голова.
     */
    private int head;

    /**
     * Хвост.
     */
    private int tail;

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        q = (E[]) new Object[arraySize];
        head = 0;
        tail = 0;
    }

    private int movePointer(int pointer) {
        int next = ++pointer;
        if (next == arraySize) {
            return 0;
        } else {
            return next;
        }
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
    public E add(E e) throws IllegalStateException {
        int tail = this.tail;
        if ((tail + 1 == head) || (head == 0 && tail + 1 == arraySize)) {
            throw new IllegalStateException("Queue is full");
        }

        q[tail] = e; /*вставляем новый элемент*/
        this.tail = movePointer(tail); /*сдвигаем хвост*/
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
    public E remove() throws NoSuchElementException {
        int head = this.head;
        if (head == tail) {
            throw new NoSuchElementException("Queue is empty");
        }
        E e = q[head];
        q[head] = null;
        this.head = movePointer(head);
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
