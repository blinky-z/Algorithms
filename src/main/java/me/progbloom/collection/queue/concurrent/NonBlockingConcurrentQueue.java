package me.progbloom.collection.queue.concurrent;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class NonBlockingConcurrentQueue<E> implements ConcurrentQueue<E> {

    public final Node<E> DUMMY = new Node<>(null, null);

    private final AtomicReference<Node<E>> head = new AtomicReference<>(DUMMY);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(DUMMY);

    @Override
    public boolean add(final E item) {
        final Node<E> newNode = new Node<E>(item, null);
        // пытаемся добавить элемент в конец очереди (проставить tail.next)
        // дальше этого CAS-цикла пройдет только один тред, а остальные зависнут, так как `tail.next` всегда будет не null, пока не сдвинем tail вперед
        while (true) {
            // необходимо каждый раз обновлять curTail, чтобы эта операция eventually прошла успешно
            // так как мы ждем, пока пишующий тред сдвинет tail вперед
            final Node<E> curTail = tail.get();
            if (curTail.next.compareAndSet(null, newNode)) {
                break;
            }
        }
        // сдвигаем tail вперед
        while (true) {
            final Node<E> curTail = tail.get();
            final Node<E> next = tail.get().next.get();
            if (tail.compareAndSet(curTail, next)) {
                break;
            }
        }
        return true;
    }

    @Override
    public int size() {
        int size = 0;
        Node<E> next = head.get().next.get();
        while (next != null) {
            size++;
            next = next.next.get();
        }
        return size;
    }

    @Override
    public List<E> getAll() {
        final List<E> items = new ArrayList<>();
        Node<E> next = head.get().next.get();
        while (next != null) {
            items.add(next.item);
            next = next.next.get();
        }
        return items;
    }

    private static class Node<E> {
        private final E item;
        private final AtomicReference<Node<E>> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }
}
