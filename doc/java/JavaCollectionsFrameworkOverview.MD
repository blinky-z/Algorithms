# Обзор структур данных Java Collections Framework

**Содержание:**
- [Краткий обзор структур данных в Java](#краткий-обзор-структур-данных-в-java)
- [Интерфейсы и их имплементации](#интерфейсы-и-их-имплементации)
    - [List](#list)
        - [Имплементации](#имплементации)
    - [Queue, Stack](#queue-stack)
        - [Имплементации](#имплементации-1)
    - [Deque](#deque)
        - [Применение](#применение)
        - [Имплементации](#имплементации-2)
        - [Полезные методы](#полезные-методы)
    - [Map](#map)
        - [Имплементации](#имплементации-3)
    - [Set](#set)
        - [Имплементации](#имплементации-4)
- [Классы](#классы)
    - [PriorityQueue](#priorityqueue)
    - [TreeMap](#treemap)
        - [Применение](#применение-1)
        - [Полезные методы](#полезные-методы-1)

---

## Краткий обзор структур данных в Java

Классы, предназначенные для использования в общих случаях:

| Interface | Hash Table | Resizable Array | LinkedList | HashTable + LinkedList |
|-----------|------------|-----------------|------------|------------------------|
| Set       | HashSet    |                 |            | LinkedHashSet          |
| List      |            | ArrayList       | LinkedList |                        |
| Deque     |            | ArrayDeque      | LinkedList |                        |
| Map       | HashMap    |                 |            | LinkedHashMap          |

Классы, предназначенные для использования в специфических случаях:

| Interface | Binary Heap   | Self-Balancing Binary Search Tree |
|-----------|---------------|-----------------------------------|
| Set       |               | TreeSet                           |
| Queue     | PriorityQueue |                                   |
| Map       |               | TreeMap                           |

Официальный обзор Java Collections Framework - [https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/doc-files/coll-overview.html](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/doc-files/coll-overview.html).

---

## Интерфейсы и их имплементации

### List

[[Interface] List](<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html>) - это интерфейс, представляющий
собой АТД «Список (List)» и предназначенный для хранения и обхода элементов в порядке вставки.

#### Имплементации

- [ArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html) на основе массива
- [LinkedList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html) на основе двусвязного списка

Из них нужно использовать ArrayList, так как она более эффективная в плане памяти и утилизации кеша. Однако, если
требуется удалять элементы с начала или в середине списка, то лучше выбрать LinkedList, так как операция удаления в
массиве требует времени O(n) против O(1) в двусвязном списке, потому что в массиве нужно смещать (shift) элементы.

---

### Queue, Stack

- [[Interface] Queue](<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html>) - это интерфейс,
  представляющий собой АТД «Очередь (queue)», которая работает по принципу FIFO
- [[Class] Stack](<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Stack.html>) - класс, имплементирующий
  АТД «Стек (queue)». Да-да, это класс, а не интерфейс: в Java нет интерфейса под чистый стек. Более того, этот класс
  очень древний, неэффективный, и вообще не используется на практике

#### Имплементации

Есть одна прямая имплементация Queue:
- [PriorityQueue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html), представляющая собой очередь с приоритетом. Построена на основе Heap

Однако, PriorityQueue совершенно не подходит для использования в качестве классической очереди.

Так что же в Java используется в качестве стека или очереди? На самом деле, в Java есть интерфейс, покрывающий оба случая: это *Deque*, который предлагает возможности как очереди, так и стека. Да, тут как-то не красиво совсем получается: в идеале Deque должен был также наследовать интерфейс Stack, но как я уже сказал, такого интерфейса в Java нет.

---

### Deque

[[Interface] Deque](<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Deque.html>) - это интерфейс, представляющий собой АТД «Дек (deque)», который может использоваться как в виде *LIFO (Last-in-First-out)*, так и *FIFO (First-in-First-out)* коллекции.

#### Применение

- Если нужна LIFO (Stack) или FIFO (Queue) коллекция

#### Имплементации

- [ArrayDeque](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayDeque.html) - unbounded имплементация на основе массива
- [Linked List](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html) - unbounded имплементация на основе двусвязного списка

Обе имплементации unbounded, то есть умеют расширяться динамически. Из них нужно использовать *ArrayDeque*, потому что она наиболее эффективная в плане представления в памяти и утилизации кэша, так как построена на основе массива.

#### Полезные методы

- addFirst, addList - добавить элемент в начало или конец дека соответственно
- peekFirst, pollFirst - если нужно посмотреть или посмотреть и удалить элемент в начале дека соответственно
- peekLast, pollLast - если нужно посмотреть или посмотреть и удалить элемент в конце дека соответственно

---

### Map

[[Interface] Map](<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html>) - это интерфейс, представляющий собой АТД «Ассоциативный массив (Словарь, Map)». Мапа позволяет хранить пары типа <ключ, значение>.

#### Имплементации

- [HashMap](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html) на основе хеширования
- [LinkedHashMap](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedHashMap.html), которая наследует HashMap с той разницей, что упорядочивает элементы в порядке их вставки путем поддержания двусвязного списка между элементами
- [TreeMap](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TreeMap.html), которая построена на основе RB Tree. В этой мапе поддерживается отсортированный порядок элементов, а операции добавления, удаления и поиска работают за гарантированное время O(log n)

---

### Set

[[Interface] Set](<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html>) предназначен для хранения уникальных значений в порядке вставки элементов.

#### Имплементации

- [HashSet](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashSet.html), который построен на основе HashMap и оперирует только ее ключами
- [LinkedHashSet](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedHashSet.html), который наследует HashSet с той разницей, что упорядочивает элементы в порядке их вставки. Таким же образом, под капотом лежит LinkedHashMap
- [TreeSet](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TreeSet.html), который построен на основе TreeMap и оперирует только ее ключами.

Все эти имплементации предоставляют такую же производительность и возможности, как лежащая под капотом мапа.

---

## Классы

### PriorityQueue

[[Class] PriorityQueue](<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html>) - это класс, который имплементирует АТД "Очередь с приоритетом" и построен на основе Binary Heap.

По-умолчанию используется natural ordering comparator, то есть очередь поддерживает самый минимальный элемент в корне - min-priority-queue (min-heap), — однако порядок можно изменить.

Хотя эта структура данных и имплементирует интерфейс Queue, не надо рассматривать эту структуру данных как FIFO коллекцию,
так как PriorityQueue предназначена для другой цели: поддерживать в голове очереди наименьший или наибольший элемент
согласно установленному компаратору, но не самый первый пришедший элемент.

Эта структура данных не предназначена для обхода элементов - ее iterator() не гарантирует никакого порядка обхода элементов.

Как я уже сказал, по-умолчанию PriorityQueue поддерживает в корне наименьший элемент, но можно поддерживать и наибольший вот так:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.<Integer>naturalOrder().reversed());
```

---

### TreeMap

[[Class] TreeMap](<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TreeMap.html>) - это мапа с отсортированным порядком элементов (sorted map), которая построена на основе Red-Black Tree. RB Tree является одним из видов самобалансирующихся деревьев поиска (self-balancing bst).

TreeMap сортирует элементы по *ключу*.

#### Применение

- Поддерживать отсортированный порядок элементов и обходить их в этом порядке
- Эффективно находить наследника (successor) или преемника (predecessor) указанного ключа

#### Полезные методы

- `lowerEntry(K)`, `floorEntry(K)`, `ceilingEntry(K)` и `higherEntry(K)` - возвращают соответственно меньший, меньший или
  равный, больший или равный, и больший элемент чем указанный ключ
- `subMap(K, boolean, K, boolean)` - возвращают сабсет мапы между low и high границей. Boolean параметры указывают,
  inclusive или exclusive границы
- `headMap(K, boolean)` и `tailMap(K, boolean)` - возвращают сабсет мапы с ключами, соответственно меньшими или большими чем
  указанный. Boolean параметры указывают, inclusive или exclusive граница
