package me.progbloom.task;

import me.progbloom.datastruct.SinglyLinkedList.Node;

/**
 * Разворот связного списка.
 * <p>
 * Идея заключается в том, чтобы на каждом шаге разворачивать указатель next на предыдущий элемент. Для этого нам
 * необходимо на каждой итерации сохранять предыдущий обработанный элемент. В начале выполнения алгоритма prev = null.
 */
public class ReverseLinkedList {

    /**
     * Разворачивает связный список.
     *
     * @param head голова связного списка
     * @param <E>  тип данных
     * @return новая голова списка
     */
    public static <E> Node<E> reverse(Node<E> head) {
        Node<E> current = head;
        Node<E> previous = null;
        while (current != null) {
            Node<E> nextElement = current.next;
            current.next = previous;
            previous = current;
            current = nextElement;
        }
        return previous;
    }
}
