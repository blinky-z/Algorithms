package me.progbloom.algo.sort;

import me.progbloom.collection.tree.BinarySearchTree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сортировка на основе Binary Search Tree
 *
 * @author Dmitry Alexandrov (alexandrov@yoomoney.ru)
 * @since 13.03.2022
 */
public class TreeSort implements AlgorithmSort {

    @Override
    public void sort(int[] a) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int elem : a) {
            tree.insert(elem);
        }

        AtomicInteger idx = new AtomicInteger();
        int[] sorted = new int[a.length];
        tree.inorderTraversal(elem -> sorted[idx.getAndIncrement()] = elem);
        System.arraycopy(sorted, 0, a, 0, a.length);
    }
}
