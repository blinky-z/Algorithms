package me.progbloom.datastruct.queue.concurrent;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class MichaelScottLockFreeConcurrentQueue<E> implements ConcurrentQueue<E> {

    public final Node<E> DUMMY = new Node<>(null, null);

    private final AtomicReference<Node<E>> head = new AtomicReference<>(DUMMY);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(DUMMY);

    @Override
    public boolean add(final E item) {
        final Node<E> newNode = new Node<>(item, null);
        while (true) {
            final Node<E> curTail = tail.get();
            // добавляем новый элемент в конец очереди (устанавливаем tail.next)
            // этот if выполнится успешно только в одном из тредов
            if (curTail.next.compareAndSet(null, newNode)) {
                // если успещно добавили новый элемент, сдвигаем tail вперед
                //
                // не важен результат cas
                // так как гарантированно пройдет или этот сдвиг A в этом же треде, или B сдвиг в другом треде
                tail.compareAndSet(curTail, newNode); /* A */
                return true;
            } else {
                final Node<E> next = curTail.next.get();
                // если не смогли добавить новый элемент, то в другом треде помогаем сдвинуть tail вперед (B)
                //
                // не важен результат cas
                // так как гарантированно пройдет или сдвиг A в пишущем треде, или этот же сдвиг в этом или другом треде
                tail.compareAndSet(curTail, next); /* B */
            }
        }
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
        List<E> items = new ArrayList<>();
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
