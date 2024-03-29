# List

**Содержание:**
- [Описание](#описание)
- [Доступные операции](#доступные-операции)
- [Порядок обхода](#порядок-обхода)
- [Имплементация](#имплементация)
- [Массивы](#массивы)
- [Динамический массив](#динамический-массив)
- [Преимущества массивов](#преимущества-массивов)

---

## Описание

**Список (List)** - это абстрактный тип данных, который предполагает хранение конечного набора значений в определенном порядке, причем каждое значение может повторяться более одного раза. Имплементации списка часто используются для имплементации более сложных структур данных, например Хэш-таблиц.

## Доступные операции

Вот некоторые операции, которые могут быть выполнены на списке:
- Проверка на пустоту списка
- Добавление элемента в конец списка
- Добавление элемента в начало списка
- Вставка элемента в середину списка (редко применимо)
- Доступ к элементу по его индексу

## Порядок обхода

Список в своем определении предполагает хранение элементов в некотором порядке, поэтому порядок всегда определен: он зависит от порядка вставки элементов в список и от места вставки.

## Имплементация

Существуют следующие наиболее популярные имплементации списка:

- Массив (Array)
- Динамический массив (Dynamic Array)
- Связный список (Linked List)
- Сбалансированное дерево (Balanced Tree)

## Массивы

Далее мы разберем две популярные имплементации списка - массив и динамический массив.

Массив - это коллекция элементов, располагающихся непрерывно в памяти, где каждый элемент идентифицируется по *индексу*.

Зная тип данных в массиве, мы можем получить адрес (в памяти) нужного элемента массива. Например, пусть массив хранит 10 элементов типа *int* (4 байта). Тогда мы можем хранить данные значения непрерывно в памяти следующим образом: 1000, 1004, 1008, ..., 1036. Адрес первого элемента называется base address и он требуется для вычисления адреса остальных элементов. Таким образом, общая формула для вычисления адреса $i$-го элемента: $\textit{baseAddress} + (\textit{sizeof}(int) * i)$

Приведу пример программы на C, выделяющей некоторый участок памяти и располагающей в ней элементы типа int:

```c
#include <stdio.h>
#include <stdlib.h>

int main ()
{
    printf ("sizeof(int)=%u\n", sizeof (int));

    int num = 2;
    char *base_address = (char *) malloc (num * sizeof (int)); /*выделяем участок памяти*/
    for (int i = 0; i < num; ++i) {
        *(base_address + sizeof (int) * i) = (i + 1) * 10; /*заполняем  его значениями, сдвигаясь на sizeof(int) вперед в цикле*/
    }

    char *first_elem = base_address; /*указатель на адрес начала первого элемента*/
    printf ("Address of the first element=%u\n", first_elem);
    printf ("Value of the first element=%u\n", (int) *first_elem);
    char *second_elem = base_address + sizeof (int); /*указатель на адрес второго, который находится дальше на sizeof(int) байт*/
    printf ("Address of the second element=%u\n", second_elem);
    printf ("Value of the second element=%u\n", (int) *second_elem);

    free (base_address);
    return 0;
}
```

Обратите внимание, что здесь мы работаем с указателем типа `char*`. Это сделано намеренно в учебных целях, чтобы вручную сдвигаться на нужное количество единичных байтов: char занимает 1 байт. А если бы мы использовали тип `int*`, то *арифметика указателей* помогала бы нам сдвигать указатель на такое количество байтов, которое занимает элемент данного типа.

Важно отметить, что доступ к элементам массива не зависит от количества элементов, то есть выполняется за константное время.

## Динамический массив

Минус массивов (статических массивов) заключается в том, что они всегда фиксированного размера и мы не можем добавлять или удалять элементы. Конечно, мы могли бы удалить старый массив и скопировать элементы в новый массив, однако мы можем инкапсулировать эту логику и ввести понятие динамического массива, который сам будет управлять выделением памяти.

**Динамический массив (dynamic array)** - это массив, который позволяет хранить произвольное количество элементов. Внутри он обычно использует для имплементации уже знакомый нам статический массив фиксированного размера, который при достижении некоторого размера переаллоцируется с увеличением размера.

К динамическому массиву применимы следующие понятия:

- *Size (logical size)* - количество сохраненных элементов в динамическом массиве
- *Capacity* - общий размер внутреннего (статического) массива. Всегда выполняется, что $\text{size} \leq \text{capacity}$

Дело в том, что используемый для имплементации статический массив всегда большего размера, чем требуется изначально, но это дает нам возможность в среднем случае добавлять и удалять элементы за константное время, так как не требуется переаллокации массива. В худшем же случае, если *size* достиг *capacity*, при вставке требуется переаллоцировать массив. Обычно в таком случае внутренний массив увеличивается в 2 раза, но все зависит от конкретной имплементации.

## Преимущества массивов

- **Хороший locality of reference и утилизация кэша процессора:** элементы расположены непрерывно на одном участке памяти. А так как кэш склонен выгружать бОльшие участки памяти при запросе какого-то отдельного участка памяти (предсказание), то непрерывное размещение элементов позволяет меньше промахиваться по кэшу, так как следующие запросы чтения пойдут в этот же участок памяти массива
- **Компактность:** массив имеет очень мало overhead. Для имплементации минимально требуется хранить только значения size и capacity
- **Random Access:** мы имеем возможность получить доступ к любой ячейке памяти за константное время
