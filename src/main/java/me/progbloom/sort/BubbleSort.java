package me.progbloom.sort;

import static me.progbloom.Utils.swap;

/**
 * Сортировка пузырьком (Bubble Sort)
 * <p>
 * Сделано несколько оптимизаций:
 * 1. Заметим, что так как после каждого прохода внешнего цикла u очередной наибольшй элемент встает на свое место, то во
 * внутреннем цикле j нам не нужно идти до конца массива, а достаточно пройтись до {@code a.length - i}.
 * 2. Введем переменную, которая будет обозначать произошло ли всплытие элемента. Если после отработки внутреннего цикла
 * ни одного свопа не было, то массив отсортирован.
 *
 * @author Dmitry Alexandrov (alexandrov@yoomoney.ru)
 * @since 13.03.2022
 */
public class BubbleSort implements AlgorithmSort {

    @Override
    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            boolean sinkedUp = false;
            for (int j = 1; j < a.length - i; j++) {
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                    sinkedUp = true;
                }
            }
            if (!sinkedUp) {
                break;
            }
        }
    }
}
