package dev.progbloom.datastruct;

import java.util.Objects;

public class SinglyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList() {
        head = tail = null;
    }

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements in this collection
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this collection contains no elements.
     *
     * @return {@code true} if this collection contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns {@code true} if this collection contains the specified element.
     * More formally, returns {@code true} if and only if this collection
     * contains at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this collection is to be tested
     * @return {@code true} if this collection contains the specified
     * element
     */
    public boolean contains(Object o) {
        Node<E> curNode = this.head;
        while (curNode != null) {
            if (Objects.equals(o, curNode)) {
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    public Node<E> insertFirst(E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> head = this.head;
        if (head != null) {
            newNode.next = head;
            this.head = newNode;
        } else {
            this.head = this.tail = newNode;
        }
        ++size;
        return newNode;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return inserted node
     */
    public Node<E> insertLast(E e) {
        Node<E> node = new Node<>(e);
        Node<E> tail = this.tail;
        if (tail != null) {
            tail.next = node;
            this.tail = node;
        } else {
            this.head = this.tail = node;
        }
        ++size;
        return node;
    }

    /**
     * Inserts the specified element after the specified node in this list.
     *
     * @param node node after which new element should be inserted
     * @param e    element to be inserted
     * @return inserted node
     */
    public Node<E> insertAfter(Node<E> node, E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = node.next;
        node.next = newNode;
        if (node == tail) {
            tail = newNode;
        }
        ++size;
        return newNode;
    }

    /**
     * Removes and returns first node of this list (head).
     * May be {@code null} if the list is empty.
     *
     * @return first node of this list if exists, {@code null} otherwise
     */
    public Node<E> removeFirst() {
        Node<E> head = this.head;

        // empty list
        if (head == null) {
            return null;
        }

        // list composed of 1 element
        if (head == tail) {
            this.head = this.tail = null;
            return head;
        }

        // replace head with successor
        Node<E> headSucc = head.next;
        this.head = headSucc;
        return head;
    }

    /**
     * Removes the element after the specified node in this list
     *
     * @param node note after which element should be removed from this collection
     * @return {@code true}
     */
    public boolean removeAfter(Node<E> node) {
        Node<E> obsoleteNode = node.next;
        // no node to delete (end of the list)
        if (obsoleteNode == null) {
            return true;
        }
        node.next = obsoleteNode.next;
        if (obsoleteNode == tail) {
            tail = node;
        }
        --size;
        return true;
    }

    public static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = Objects.requireNonNull(value, "Value must not be null!");
            next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                "value=" + value +
                '}';
        }
    }
}