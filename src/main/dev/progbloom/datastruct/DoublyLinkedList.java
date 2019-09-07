package dev.progbloom.datastruct;

import java.util.Objects;

/**
 * Singly linked list implementation.
 * Link: {@code https://progbloom.dev/posts/28}
 */
public class DoublyLinkedList<E> implements Collection<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements in this collection
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this collection contains no elements.
     *
     * @return {@code true} if this collection contains no elements
     */
    @Override
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
    @Override
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
     * Finds first occurrence of the element with such key.
     *
     * @param key key
     * @return element with such key or {@code null} if no found
     */
    public Node<E> search(E key) {
        Node<E> curNode = this.head;
        while (curNode != null) {
            if (curNode.key == key) {
                break;
            }
            curNode = curNode.next;
        }
        return curNode;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return {@code true} as the collection always changes
     */
    @Override
    public boolean add(E e) {
        Node<E> node = new Node<>(e);
        Node<E> tail = this.tail;
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
        } else {
            tail = node;
        }
        this.tail = tail;
        size++;
        return true;
    }

    /**
     * Inserts the specified element before the specified node in this list.
     *
     * @param node node before which new element should be inserted
     * @param e    element to be inserted
     * @return {@code true} as the collection always changes
     */
    public boolean addBefore(Node<E> node, E e) {
        Node<E> newNode = new Node<>(e);
        newNode.prev = node.prev;
        newNode.next = node;
        node.prev = newNode;
        size++;
        return true;
    }

    /**
     * Removes this node from the linked list.
     *
     * @param o element to be removed from this collection
     * @return {@code true} as it always changes the collection
     */
    @Override
    public boolean remove(Object o) {
        Node node = (Node) o;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            this.head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            this.tail = node.prev;
        }
        size--;
        return true;
    }

    /**
     * Removes the element with such key from the collection.
     * <p>
     * If there's no element with such key, then {@code false} will be returned.
     *
     * @param key key
     * @return {@code true} if the element was deleted, {@code false} otherwise
     */
    public boolean removeByKey(E key) {
        Node<E> node = search(key);
        if (node != null) {
            return remove(node);
        } else {
            return false;
        }
    }

    public static class Node<E> {
        final E key;
        Node<E> prev, next;

        Node(E key) {
            this.key = key;
            prev = next = null;
        }
    }

    public DoublyLinkedList() {
        head = tail = null;
    }
}
