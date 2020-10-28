package me.progbloom.datastruct.queue;

import me.progbloom.datastruct.Deque;
import me.progbloom.datastruct.DoublyLinkedList;

public class LinkedListDeque<E> implements Deque<E> {

    private DoublyLinkedList<E> linkedList;

    public LinkedListDeque() {
        this.linkedList = new DoublyLinkedList<>();
    }

    @Override
    public void addFirst(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public void addLast(E e) {
        linkedList.addLast(e);
    }

    @Override
    public E removeFirst() {
        return linkedList.removeFirst().key;
    }

    @Override
    public E removeLast() {
        return linkedList.removeLast().key;
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
