package me.progbloom.collection.stack;

import me.progbloom.collection.linkedlist.SinglyLinkedList;
import me.progbloom.collection.linkedlist.SinglyLinkedList.Node;

import java.util.EmptyStackException;

public class LinkedListStack<E> implements Stack<E> {

    private SinglyLinkedList<E> linkedList;

    public LinkedListStack() {
        this.linkedList = new SinglyLinkedList<>();
    }

    @Override
    public E push(E item) {
        linkedList.insertFirst(item);
        return item;
    }

    @Override
    public E pop() {
        Node<E> node = linkedList.removeFirst();
        if (node == null) {
            throw new EmptyStackException();
        }
        return node.value;
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
