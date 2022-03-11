package me.progbloom.datastruct.tree;


import java.util.Optional;

/**
 * Имплементация бинарного дерева поиска (Binary Search Tree, BST).
 * <p>
 * Для BST выполняются следующие условия:
 * 1. Оба поддерева — левое и правое — являются двоичными деревьями поиска
 * 2. У всех узлов левого поддерева произвольной ноды X ключи меньше, чем ключ самой ноды X
 * 3. У всех узлов правого поддерева произвольной ноды X ключи больше либо равны, чем ключ самой ноды X
 */
public class BinarySearchTree {

    /**
     * Корень дерева
     */
    private Node root;

    /**
     * Ищет ноду с указанным ключом (рекурсивная имплементация)
     *
     * @param k искомый ключ
     * @return нода с этим ключом
     */
    public Optional<Node> search(Node root, int k) {
        // если в ходе похода в левое или правое поддерево мы обнаружим, что нода = null, то искомого элемента в дереве нет
        if (root == null) {
            return Optional.empty();
        }
        if (root.key == k) {
            return Optional.of(root);
        }

        // если искомый элемент меньше текущего элемента, то идем в левое поддерево, так как там лежат элементы меньшие чем текущий
        if (k < root.key) {
            return search(root.left, k);
        } // иначе идем в правое поддерево, так как там лежат элементы большие чем текущий
        else {
            return search(root.right, k);
        }
    }

    /**
     * Ищет ноду с указанным ключом (итеративная имплементация)
     *
     * @param k искомый ключ
     * @return нода с этим ключом
     */
    public Optional<Node> searchIterative(int k) {
        Node x = root;
        while (x != null && x.key != k) {
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return Optional.ofNullable(x);
    }

    /**
     * Вставляет новое значение в дерево
     * <p>
     * Вставка происходит путем добавления листа к дереву.
     *
     * @param k значение
     */
    public void insert(int k) {
        Node y = null;
        Node x = root;
        // находим позицию для вставки листа
        // нам нужно вставить лист так, чтобы он не нарушил свойства
        while (x != null) {
            y = x;
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        // вставляем лист
        Node newNode = new Node(k);
        newNode.parent = y;
        if (y == null) {
            root = newNode; // дерево было пустым
        } else if (k < y.key) {
            y.left = newNode;
        } else {
            y.right = newNode;
        }
    }

    /**
     * Ищет наименьшее значение в дереве (рекурсивная имплементация)
     *
     * @param root корень дерева
     * @return нода с наименьшим значением
     */
    private Node minimum(Node root) {
        if (root.left == null) {
            return root;
        } else {
            return minimum(root.left);
        }
    }

    /**
     * Ищет наименьшее значение в дереве (итеративная имплементация)
     *
     * @param root корень дерева
     * @return нода с наименьшим значением
     */
    private Node minimumIterative(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * Ищет наибольшее значение в дереве (рекурсивная имплементация)
     *
     * @param root корень дерева
     * @return нода с наибольшим значением
     */
    private Node maximum(Node root) {
        if (root.right == null) {
            return root;
        } else {
            return maximum(root.right);
        }
    }

    /**
     * Ищет наибольшее значение в дереве (итеративная имплементация)
     *
     * @param root корень дерева
     * @return нода с наибольшим значением
     */
    private Node maximumIterative(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    /**
     * Получает преемника указанного элемента - то есть наибольший меньший элемент перед текущим
     *
     * @param x нода
     * @return преемник
     */
    public Node predecessor(Node x) {
        // если существует левое поддерево, в котором все ключи по определению меньше чем в текущей ноде
        // то возвращаем максимум из этого поддерева
        if (x.left != null) {
            return maximum(x.left);
        }

        // если левого поддерева нет, а в правом все элементы по определению больше чем в текущей ноде
        // то поднимаемся по дереву до тех пор, пока не найдем такого родителя, для которого окажется, что он -
        // правая нода его родителя
        Node y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * Получает наследника указанного элемента - то есть наименьший больший элемент за текущим
     *
     * @param x нода
     * @return наследник
     */
    public Node successor(Node x) {
        // если существует правое поддерево, в котором все ключи по определению больше чем в текущей ноде
        // то возвращаем минимум из этого поддерева
        if (x.right != null) {
            return minimum(x.right);
        }

        // если правого поддерева нет, а в левом все элементы по определению меньше чем в текущей ноде
        // то поднимаемся по дереву до тех пор, пока не найдем такого родителя, для которого окажется, что он -
        // левая нода его родителя
        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * In-order обход дерева
     * <p>
     * Такой обход позволяет обойти элементы дерева в неубывающем порядке ключей.
     *
     * @param root корень дерева
     */
    public void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.key);
            inorderTraversal(root.right);
        }
    }
}
