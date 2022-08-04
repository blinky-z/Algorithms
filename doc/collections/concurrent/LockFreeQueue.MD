# Lock-Free Queue (Michael-Scott Queue)

Здесь мы разберем lock-free очередь Майкла-Скотта. Для понимания этой статьи потребуется понимание [compare-and-swap (CAS)](https://en.wikipedia.org/wiki/Compare-and-swap) операции.

## Add Operation: Introduction

Операция добавления работает в 2 шага:
1. Добавление нового элемента в конец очереди, т.е. проставление tail.next на новый элемент
2. Сдвигание tail, т.е. обновление ссылки tail на tail.next.

Давайте для начала имплементируем блокирующую операцию добавления:
```java
@ThreadSafe
public class BlockingQueue<E> implements ConcurrentQueue<E> {

    private final Node<E> DUMMY = new Node<>(null, null);

    private final Node<E> head = DUMMY;
    private Node<E> tail = head;

    @Override
    public synchronized boolean add(final E item) {
        final Node<E> newNode = new Node<>(item, null);
        tail.next = newNode;
        tail = tail.next;
        size++;
        return true;
    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
```

## Add Operation: Non-Blocking

Теперь давайте имплементируем неблокирующую операцию добавления.

Это будет выглядеть так:
```java
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

    private static class Node<E> {
        private final E item;
        private final AtomicReference<Node<E>> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }
}
```

Заметим, что если 1 тред уже успел добавить новый элемент, но еще не сдвинул tail, то все другие треды, которые начнут выполнять add после, зависнут в ожидании на первом CAS-лупе, так как `tail.get().next` будет равен ссылке на новый ранее добавленный элемент, а не `null` как ожидается в CAS.
А теперь заметим еще то, что если планировщик вытесняет первый поток, который добавил новый элемент, то весь прогресс встанет, т.к. другие треды будут заблокированы в ожидании просыпания первого потока. Именно поэтому этот алгоритм не является lock-free.

На всякий случай повторим условия lock-free алгоритма:
1. Гарантированный system-wide прогресс
2. Если один поток вымещается планировщиком, это не должно помешать выполнению прогресса другими потоками

## Add Operation: Lock-free

Теперь имплементируем lock-free операцию добавления:
```java
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

    private static class Node<E> {
        private final E item;
        private final AtomicReference<Node<E>> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }
}
```

В этой имплементации другие треды, если провалили добавление нового элемента, просто помогают сдвинуть `tail` вперед (`B`). Эта имплементация является lock-free потому, что даже если пишущий тред был вытеснен планировщиком, то другие треды завершат работу по сдвигу tail за него, то есть гарантируется system-wide прогресс и вымещение первого пишущего потока не имеет эффекта на прогресс других потоков.

Наблюдения:
- Если первый тред смог сделать `С` и затем заснул, а в это время другой тред уже сдвинул `tail` в CAS `B`, то проснувшись первый тред просто провалит CAS `A`, так как `curTail` уже не является актуальным `tail`.

---

## Ресурсы

- [Очередь Майкла и Скотта](https://neerc.ifmo.ru/wiki/index.php?title=%D0%9E%D1%87%D0%B5%D1%80%D0%B5%D0%B4%D1%8C_%D0%9C%D0%B0%D0%B9%D0%BA%D0%BB%D0%B0_%D0%B8_%D0%A1%D0%BA%D0%BE%D1%82%D1%82%D0%B0)
- Paper [Simple, Fast, and Practical Non-Blocking and Blocking Concurrent Queue Algorithms](https://www.cs.rochester.edu/~scott/papers/1996_PODC_queues.pdf) от собственно самих Майкла (Maged M. Michael) и Скотта (Michael L. Scott)
- [Introduction to nonblocking algorithms, Brian Goetz](https://web.archive.org/web/20201109040328/https://www.ibm.com/developerworks/java/library/j-jtp04186/)
- [Слайды про Concurrent Linked Lists](https://www.cse.wustl.edu/~angelee/archive/cse539/spr15/lectures/lists.pdf)
