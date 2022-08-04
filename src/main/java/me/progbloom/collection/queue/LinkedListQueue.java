package me.progbloom.collection.queue;

import me.progbloom.collection.linkedlist.SinglyLinkedList;

/**
 * Имплементация очереди на основе связного списка.
 *
 * @param <E> тип данных
 */
public class LinkedListQueue<E> implements Queue<E> {

    private final SinglyLinkedList<E> linkedList;

    public LinkedListQueue() {
        this.linkedList = new SinglyLinkedList<>();
    }

    @Override
    public E add(E e) {
        linkedList.insertLast(e);
        return e;
    }

    @Override
    public E remove() {
        return linkedList.removeFirst().value;
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
