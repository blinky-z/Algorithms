# Устройство Java объектов в памяти

В данной статье мы рассмотрим, как устроены Java объекты в памяти.

Стандарт Java не указывает, какой должна быть организация памяти объектов, поэтому мы рассмотрим эти аспекты на примере open-source HotSpot JVM.

## Java Object

Структура, описывающая Java Object, который неявно является родительским классом для всех объектов в Java, состоит из *Mark Word* и *Klass Word*.

***Mark word*** - это структура, которая выступает в роли хэдеров объекта. JVM хранит здесь:

- *Identity Hash Code* - кэш-код, возвращаемый методом [System.identityHashCode(Object x)](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#identityHashCode-java.lang.Object-) или дефолтным методом [hashCode()](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--) объекта, если не был переопределен.
- *Biased lock pattern* - требуется для реализации Biased Locking, т.е. чтобы привязать (bias) лок к треду.
- *Лок* - каждый объект в Java может быть использован в качестве монитора (intrinsic lock), и реализовано это путем хранения лока в хэдере объекта
- *Метаданные для GC* - время жизни объекта, а точнее, счетчик пережитых сборок мусора. После определенного значения пережитых сборок объект перемещается в Old Generation.

***Klass Word*** - это *указатель* на область памяти в Metaspace, где хранится информация о классе: имя класса, его поля, информация о родительском классе и так далее. Другими словами, это `java.lang.Class`, который мы можем получить вызовом [getClass()](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#getClass--) на объекте.

Все это мы можем подсмотреть из исходников HotSpot JVM, находящихся в свободном доступе на [GitHub](https://github.com/openjdk/jdk15/tree/e208d9aa1f185c11734a07db399bab0be77ef15f/src/hotspot). Базовая структура Java объекта описана в файле [hotspot/share/oops/oop.hpp](https://github.com/openjdk/jdk15/blob/e208d9aa1f185c11734a07db399bab0be77ef15f/src/hotspot/share/oops/oop.hpp):

```cpp
class oopDesc {
  friend class VMStructs;
  friend class JVMCIVMStructs;
 private:
  volatile markWord _mark;
  union _metadata {
    Klass*      _klass;
    narrowKlass _compressed_klass;
  } _metadata;
....
}
```

Итак, отсюда мы видим, что структура `oopDesc` содержит поле типа `markWord` и union _metadata, хранящую указатель на `Klass`. Давайте взглянем на структуру markWord поближе. Она описана в файле [hotspot/share/oops/markWord.hpp](https://github.com/openjdk/jdk15/blob/e208d9aa1f185c11734a07db399bab0be77ef15f/src/hotspot/share/oops/markWord.hpp):

```cpp
class markWord {
 private:
  uintptr_t _value;
....
}
```

Мы видим, что состояние структуры - всего одно поле типа `uintptr_t`, а не несколько различных полей для хранения хэдеров. Это интересно, но не удивительно: нам необходимо сделать структуру объекта, являющегося родительским классом для всех классов в Java, как можно легче. Поэтому все необходимые данные извлекаются и устанавливается в хэдере с помощью техники известной как *Bit Masking*: мы просто устанавливаем нужные биты в поле `_value`.

На самом же деле структура oopDesc является родительской для еще двух структур: для инстансов классов и для массивов объектов.

Это мы можем узнать из файла [hotspot/share/oops/**oopsHierarchy.hpp**](https://github.com/openjdk/jdk15/blob/e208d9aa1f185c11734a07db399bab0be77ef15f/src/hotspot/share/oops/oopsHierarchy.hpp):

```cpp
// OBJECT hierarchy
// This hierarchy is a representation hierarchy, i.e. if A is a superclass
// of B, A's representation is a prefix of B's representation.

typedef class oopDesc*                    oop;
typedef class   instanceOopDesc*            instanceOop;
typedef class   arrayOopDesc*               arrayOop;
typedef class     objArrayOopDesc*            objArrayOop;
typedef class     typeArrayOopDesc*           typeArrayOop;
```

Нас интересуют классы `instanceOopDesc` и `arrayOopDesc`. Первый класс используется для всех инстансов классов, кроме `Object`, например вызов `new HashMap()` вернет нам `instanceOopDesc`. А вторая структура используется для массивов объектов - так, вызов `new Integer[N]` вернет нам `arrayOopDesc`.

Взглянем на объявление первого класса, содержащееся в файле [hotspot/share/oops/**instanceOop.hpp**](https://github.com/openjdk/jdk15/blob/master/src/hotspot/share/oops/instanceOop.hpp):

```cpp
// An instanceOop is an instance of a Java Class
// Evaluating new HashTable() will create an instanceOop.

class instanceOopDesc : public oopDesc {
 public:
  // aligned header size.
  static int header_size() { return sizeof(instanceOopDesc)/HeapWordSize; }

  // If compressed, the offset of the fields of the instance may not be aligned.
  static int base_offset_in_bytes() {
    if (UseNewFieldLayout) {
      return (UseCompressedClassPointers) ?
              klass_gap_offset_in_bytes() :
              sizeof(instanceOopDesc);
    } else {
      // The old layout could not deal with compressed oops being off and compressed
      // class pointers being off.
      return (UseCompressedOops && UseCompressedClassPointers) ?
              klass_gap_offset_in_bytes() :
              sizeof(instanceOopDesc);
    }
  }
};
```

Мы видим, что этот класс только добавляет новые методы, которые нас не очень интересуют.

Посмотрим же на второй класс `arrayOopDesc`, объявление которого содержится в файле [hotspot/share/oops/**arrayOop.hpp**](https://github.com/openjdk/jdk15/blob/e208d9aa1f185c11734a07db399bab0be77ef15f/src/hotspot/share/oops/arrayOop.hpp):

```cpp
// arrayOopDesc is the abstract baseclass for all arrays.  It doesn't
// declare pure virtual to enforce this because that would allocate a vtbl
// in each instance, which we don't want.

// The layout of array Oops is:
//
//  markWord
//  Klass*    // 32 bits if compressed but declared 64 in LP64.
//  length    // shares klass memory or allocated after declared fields.

class arrayOopDesc : public oopDesc {
  friend class VMStructs;
  friend class arrayOopDescTest;

  // Interpreter/Compiler offsets

  // Header size computation.
  // The header is considered the oop part of this type plus the length.
  // Returns the aligned header_size_in_bytes.  This is not equivalent to
  // sizeof(arrayOopDesc) which should not appear in the code.
  static int header_size_in_bytes() {
...
```

Итак, документация говорит нам, что структура массива объектов содержит все поля из `oopDesc` и в дополнение *длину массива* (length). Однако, это поле не объявлено явно.

Метод `length_offset_in_bytes()`, возвращающий offset, с которого в участке памяти этой структуры начинается значение длины, сообщает нам как оно аллоцируется:

```cpp
 public:
  // The _length field is not declared in C++.  It is allocated after the
  // declared nonstatic fields in arrayOopDesc if not compressed, otherwise
  // it occupies the second half of the _klass field in oopDesc.
  static int length_offset_in_bytes() {
    return UseCompressedClassPointers ? klass_gap_offset_in_bytes() :
                               sizeof(arrayOopDesc);
  }
```

Мы можем также увидеть, что длина вычисляется путем интерпретирования участка памяти:

```cpp
  // Accessors for instance variable which is not a C++ declared nonstatic
  // field.
  int length() const {
    return *(int*)(((intptr_t)this) + length_offset_in_bytes());
  }
```

## Анализ размера объектов

Итак, мы разобрали исходники и узнали, что и как содержится в структуре объекта. Теперь посчитаем, сколько это все стоит по памяти. Давайте сначала попытаемся проанализировать, основываясь на ранее полученных наблюдениях, а затем проверить на практике.

Итак, класс `oopDesc`:

Поле `volatile markWord _mark` - это структура, состоящая из единственного поля поля типа `uintptr_t`. Это поле занимает 32 бита в 32-битной системе (4 байта) и 64 бита (8 байт) в 64-битной системе. Почему HotSpot JVM понадобился разный размер этого поля для 32 битной и 64 битной систем? Дело в том, что если произошел biased locking, то организация данных в markWord меняется и в него кладется указатель на `JavaThread`, а указатели зависят от битности.

Мы можем это увидеть из документации к классу `markWord`:

```cpp
// The markWord describes the header of an object.
//
// Bit-format of an object header (most significant first, big endian layout below):
//
//  32 bits:
//  --------
//             hash:25 ------------>| age:4    biased_lock:1 lock:2 (normal object)
//             JavaThread*:23 epoch:2 age:4    biased_lock:1 lock:2 (biased object)
//
//  64 bits:
//  --------
//  unused:25 hash:31 -->| unused_gap:1   age:4    biased_lock:1 lock:2 (normal object)
//  JavaThread*:54 epoch:2 unused_gap:1   age:4    biased_lock:1 lock:2 (biased object)
```

Второе поле - это union (объединение) из 2-х полей:

```cpp
  union _metadata {
    Klass*      _klass;
    narrowKlass _compressed_klass;
  } _metadata;
```

Для справки, union - это механизм C++, позволяющий хранить ровно одно поле из объявленных в union, то есть размер union зависит от того, какое поле используется.

 Здесь следует вспомнить о [Compressed Oops](https://wiki.openjdk.java.net/display/HotSpot/CompressedOops) - оптимизации Java, позволяющей сжимать указатели до 32 бит в 64-битной системе. Дело в том, что объединение _metadata будет хранить явно указатель на `Klass`, если сжатие указателей не включено, и `narrowKlass` если сжатие включено. Структура `narrowKlass` это typedef на `juint`, как мы узнаем из файла **[oopsHierarchy.hpp](https://github.com/openjdk/jdk15/blob/e208d9aa1f185c11734a07db399bab0be77ef15f/src/hotspot/share/oops/oopsHierarchy.hpp#L39)**:

```cpp
typedef juint  narrowKlass;
```

А `juint`, в свою очередь, является typedef на `u4`, как мы узнаем из файла [hotspot/share/utilities/**globalDefinitions.hpp**](https://github.com/openjdk/jdk15/blob/e208d9aa1f185c11734a07db399bab0be77ef15f/src/hotspot/share/utilities/globalDefinitions.hpp):

```cpp
// Unsigned one, two, four and eigth byte quantities used for describing
// the .class file format. See JVM book chapter 4.

typedef jubyte  u1;
typedef jushort u2;
typedef juint   u4;
typedef julong  u8;
```

Кратко: это типы, представляющие собой значения размером 1, 2, 4 и 8 байт соответственно. Подробнее здесь - [Java Virtual Machine Specification](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html).

Так мы понимаем, что в случае сжатия указателей используется поле `_compressed_klass` длиной 4 байта.

Подведем итог:

- *Mark Word* имеет размер в 4 байта на 32-битной и 8 байт на 64-битной системе.
- *Klass Word* имеет размер в 4 байта на 32-битной системе, а на 64-битной - 4 байта при включенном сжатии указателей и 8 байт при выключенном сжатии

Давайте проверим это на практике. Для этого мы заиспользуем утилиту [JOL (Java Object Layout)](https://github.com/openjdk/jol), позволяющую показать организацию любого Java класса в памяти, с учетом padding и align. Посмотрим на базовый класс `java.lang.Object`:

```bash
$ java -XX:+UseCompressedOops -jar jol-cli.jar internals java.lang.Object
# Running 64-bit HotSpot VM.
# Using compressed oop with 3-bit shift.
# Using compressed klass with 0x0000000800000000 base address and 0-bit shift.
# Objects are 8 bytes aligned.
# Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
# Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]

Instantiated the sample instance via default constructor.

java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           00 10 00 00 (00000000 00010000 00000000 00000000) (4096)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
```

Итак, мы видим, что на 64-битной системе хэдер занимает 12 байт (8 байт markWord + 4 байта сжатый Klass pointer), но для align под машинное слово JVM добавляет 4 байта, в итоге получая размер в 16 байт. Мы можем подумать, что сжатие Klass Pointer было быссмысленным, ведь мы бы в любом случае получили 16 байт, однако стоит учитывать, что это только базовый класс Object. Для инстанса `Integer`, который наследует Object, мы получаем свободное пространство для размещения числового значения:

```bash
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           e8 39 01 00 (11101000 00111001 00000001 00000000) (80360)
     12     4    int Integer.value                             0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

### Анализ массива объектов

Давайте теперь проанализируем стоимость массива объектов. Мы помним, что в его структуре добавляется 4 байта для хранения длины массива. Давайте же посмотрим на организацию памяти массива объектов:

```bash
$ java -XX:+UseCompressedOops -jar jol-cli.jar internals [I
[I object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0    16        (object header)                           N/A
     16     0    int [I.<elements>                             N/A
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

**[I** - это массив объектов типа Integer. Как мы видим, хэдер занял 16 байт - 8 байт markWord + 4 байта Klass Pointer + 4 байта длина массива -, как и ожидалось.