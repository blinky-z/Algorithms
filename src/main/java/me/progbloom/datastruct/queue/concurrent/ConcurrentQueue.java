package me.progbloom.datastruct.queue.concurrent;

import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public interface ConcurrentQueue<E> {

    boolean add(final E item);

    int size();

    List<E> getAll();
}
