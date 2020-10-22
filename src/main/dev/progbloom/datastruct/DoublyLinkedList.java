package dev.progbloom.datastruct;

import java.util.Objects;

/**
 * Doubly linked list implementation.
 */
public class DoublyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {
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

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return inserted node
     */
    public Node<E> addLast(E e) {
        Node<E> node = new Node<>(e);
        Node<E> tail = this.tail;
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
            this.tail = node;
        } else {
            this.head = this.tail = node;
        }
        size++;
        return node;
    }

    /**
     * Inserts the specified element before the specified node in this list.
     *
     * @param node node before which new element should be inserted
     * @param e    element to be inserted
     * @return inserted node
     */
    public Node<E> addBefore(Node<E> node, E e) {
        Node<E> newNode = new Node<>(e);
        newNode.prev = node.prev;
        newNode.next = node;
        if (node.prev == null) { // если переданная нода является головой, то сместить голову на новый элемент
            this.head = newNode;
        }
        node.prev = newNode;
        size++;
        return newNode;
    }

    /**
     * Inserts the specified element after the specified node in this list.
     *
     * @param node node after which new element should be inserted
     * @param e    element to be inserted
     * @return inserted node
     */
    public Node<E> addAfter(Node<E> node, E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = node.next;
        newNode.prev = node;
        if (node.next == null) { // если переданная нода является хвостом, то сместить хвост на новый элемент
            this.tail = newNode;
        }
        node.next = newNode;
        ++size;
        return newNode;
    }

    /**
     * Removes this node from the linked list.
     *
     * @param node element to be removed from this collection
     * @return {@code true}
     */
    public boolean remove(Node<E> node) {
        if (node.prev != null) {
            node.prev.next = node.next; // предыдущий элемент теперь указывает ноду, следующую за удаляемой
        } else {
            this.head = node.next; // если переданная нода является головой, то смещаем голову
        }
        if (node.next != null) {
            node.next.prev = node.prev; // следующий элемент теперь указывает на ноду, предшествующую удаляемой
        } else {
            this.tail = node.prev; // если переданная нода является хвостом, то смещаем хвост
        }
        ++size;
        return true;
    }

    /**
     * Removes and returns first node of this list (head).
     * May be {@code null} if the list is empty.
     *
     * @return first node of this list if exists, {@code null} otherwise
     */
    public Node<E> removeFirst() {
        Node<E> head = this.head;
        if (head == null) { // пустой список
            return null;
        }
        if (head == tail) { // список состоит из 1 элемента
            this.head = this.tail = null;
            return head;
        }

        // заменить голову следующим элементом
        Node<E> headSucc = head.next;
        headSucc.prev = null;
        this.head = headSucc;
        return head;
    }

    /**
     * Removes and returns last node of this list (tail).
     * May be {@code null} if the list is empty.
     *
     * @return last node of this list if exists, {@code null} otherwise
     */
    public Node<E> removeLast() {
        Node<E> tail = this.tail;
        if (tail == null) { // пустой список
            return null;
        }
        if (tail == this.head) { // список состоит из 1 элемента
            this.head = this.tail = null;
            return tail;
        }

        // заменить хвост предшествующим элементом
        Node<E> tailPred = tail.prev;
        tailPred.next = null;
        this.tail = tailPred;
        return tail;
    }

    public static class Node<E> {
        E key;
        Node<E> prev, next;

        Node(E key) {
            this.key = key;
            prev = next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                "key=" + key +
                '}';
        }
    }
}
