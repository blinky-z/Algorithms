package me.progbloom.task;

import me.progbloom.collection.stack.Stack;
import me.progbloom.collection.queue.ArrayQueue;
import me.progbloom.collection.queue.Queue;

/**
 * Имплементация стека с помощью двух очередей.
 *
 * Идея заключается в следующем: есть 2 очереди. Каждый раз в первой очереди первым элементом всегда лежит последний
 * пришедший элемент, и именно его мы забираем при удалении. Если приходит новый элемент, то мы перемещаем все из первой
 * очереди во вторую - вспомогательную - при этом порядок сохраняется, и вставляем новый элемент в первую очередь. Затем
 * перемещаем элементы обратно из второй очереди в первую. Получается, что первым в первой очереди лежит новый пришедший
 * элемент, а остальные пришедшие ранее элементы сохранены в таком же порядке за ним.
 *
 * @param <E> тип данных
 */
public class StackUsingTwoQueues<E> implements Stack<E> {

    private final Queue<E> q1;
    private final Queue<E> q2;

    public StackUsingTwoQueues() {
        q1 = new ArrayQueue<>();
        q2 = new ArrayQueue<>();
    }

    @Override
    public E push(E e) {
        if (q1.isEmpty()) {
            q1.add(e);
            return e;
        }
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        q1.add(e);
        while (!q2.isEmpty()) {
            q1.add(q2.remove());
        }
        return e;
    }

    @Override
    public E pop() {
        return q1.remove();
    }

    @Override
    public boolean isEmpty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}
