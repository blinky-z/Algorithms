**Дек (Deque)** - это абстрактный тип данных, который вбирает в себя возможности как стека, так и очереди, то есть может работать с элементами как по LIFO, так и по FIFO принципу.

## Операции на деке

Дек должен имеет следующие операции:

- Вставка в начало (pushFront)
- Вставка в конец (pushBack)
- Удаление элемента с начала (popFront)
- Удаление элемента с конца (popBack)

## Имплементация

Дек можно имплементировать с помощью кольцевого буфера или связного списка.

### Имплементация на основе кольцевого буфера (bounded)

Данная имплементация дека ограничена по размеру (bounded).

Идея данной имплементации заключается в следующем: мы заведем кольцевой буфер и будем поддерживать два указателя - на голову и хвост списка. Голова растет с начала массива и крутится по кольцевому буферу по часовой стрелке, а хвост растет с конца массива и крутится против часовой стрелки, таким образом они будут идти навстречу друг другу. Однако, при инициализации оба указателя будут указывать на нулевую ячейку массива, и только при первой вставке голова начнет расти с начала массива, а хвост - с его конца.

Вот как мы обработаем заполненность дека:
1. **Полный дек:** мы знаем, что дек заполнен, если:
   - голова указывает на последнюю ячейку массива, а хвост на нулевую - `head == array_size - 1; tail == 0`. В такой ситуации вставка не возможна, так как хвост или голова могут перекрыть друг друга
   - голова при смещении впереди будет равна хвосту (хвост при смещении назад будет равен голове) - `head + 1 == tail`
2. **Пустой дек:** мы знаем, что дек пуст, если указатели указывают на одну и ту же ячейку массива - `head == tail`

Итак, инициализация:
```java
    public ArrayDeque() {
        arr = new Integer[arraySize];
        head = 0;
        tail = 0;
    }
```

*Вставка в начало* работает следующим образом: мы вставляем новый элемент в ячейку, куда указывает `head`, а затем сдвигаем `head` вперед:
```java
    public void addFirst(Integer e) {
        int head = this.head;
        if (((head == arraySize - 1 && tail == 0) || (head + 1 == tail))) {
            throw new IllegalStateException(Deque is full);
        }
        arr[head] = e;
        this.head = nextHead(head);
    }
    
    private int nextHead(int head) {
        if (head == arraySize - 1) {
            return 0;
        }
        return ++head;
    }
```

*Удаление с начала* сдвигает head назад, а затем забирает элемент из ячейки:
```java
    public Integer removeFirst() {
        int head = this.head;
        if (head == tail) {
            throw new NoSuchElementException(Deque is empty);
        }
        head = prevHead(head);
        Integer elem = arr[head];
        arr[head] = null;
        this.head = head;
        return elem;
    }

    private int prevHead(int head) {
        if (head == 0) {
            return arraySize - 1;
        }
        return --head;
    }
```

Метод вставки в конец почти не отличается от вставки в начало. Разница лишь в том, что мы сначала сдвигаем `tail` вперед и только затем вставляем элемент. Так необходимо делать потому, что при инициализации оба указателя указывают на нулевую ячейку массива, но хвост должен начать расти с конца массива. 

*Вставка в конец*:
```java
    public void addLast(Integer e) {
        int tail = this.tail;
        if (((head == arraySize - 1 && tail == 0) || (head + 1 == tail))) {
            throw new IllegalStateException(Deque is full);
        }
        tail = nextTail(tail);
        arr[tail] = e;
        this.tail = tail;
    }
   
    private int nextTail(int tail) {
        if (tail == 0) {
            return arraySize - 1;
        }
        return --tail;
    }
```

*Удаление с конца* забирает элемент из ячейки `tail`, а затем сдвигает tail назад:
```java
    public Integer removeLast() {
        int tail = this.tail;
        if (head == tail) {
            throw new NoSuchElementException(Deque is empty);
        }
        Integer elem = arr[tail];
        arr[tail] = null;
        this.tail = prevTail(tail);
        return elem;
    }

    private int prevTail(int tail) {
        if (tail == arraySize - 1) {
            return 0;
        }
        return ++tail;
    }
```

### Имплементация на основе связного списка

Для этой имплементации нам понадобится двусвязный список.

Имплементация получается довольно тривиальной.

Инициализация:
```java
    public LinkedListDeque() {
        this.linkedList = new DoublyLinkedList<>();
    }
```

Добавление и удаление с начала:
```java
    public void addFirst(E e) {
        linkedList.addFirst(e);
    }

    public E removeFirst() {
        return linkedList.removeFirst().key;
    }
```

Добавление и удаление с конца:
```java
    public void addLast(E e) {
        linkedList.addLast(e);
    }

    public E removeLast() {
        return linkedList.removeLast().key;
    }
```
