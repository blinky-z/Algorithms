package me.progbloom.datastruct.queue.concurrent;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class BlockingConcurrentQueue<E> implements ConcurrentQueue<E> {

    private final Node<E> DUMMY = new Node<>(null, null);

    private final Node<E> head = DUMMY;
    private Node<E> tail = head;

    private int size = 0;

    @Override
    public synchronized boolean add(final E item) {
        final Node<E> newNode = new Node<>(item, null);
        tail.next = newNode;
        tail = tail.next;
        size++;
        return true;
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized List<E> getAll() {
        final List<E> items = new ArrayList<>();
        Node<E> next = head.next;
        while (next != null) {
            items.add(next.item);
            next = next.next;
        }
        return items;
    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
