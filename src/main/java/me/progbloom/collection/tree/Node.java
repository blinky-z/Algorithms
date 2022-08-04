package me.progbloom.collection.tree;

/**
 * Представление узла дерева (vertex, node).
 */
public class Node {

    /**
     * Ключ в ноде
     */
    int key;

    /**
     * Левый ребенок
     */
    Node left;

    /**
     * Правый ребенок
     */
    Node right;

    /**
     * Родитель
     */
    Node parent;

    public Node(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "key=" + key +
                '}';
    }
}
