package me.progbloom.datastruct;


/**
 * Binary search tree (BST) implementation.
 */
public class BinarySearchTree {

    private TreeNode root;

    private void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.key);
            inorderTraversal(root.right);
        }
    }

    public void inorderTraversal() {
        inorderTraversal(root);
    }

    public void insert(int k) {
        TreeNode y = null;
        TreeNode x = root;
        while (x != null) {
            y = x;
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        TreeNode newNode = new TreeNode(k);
        newNode.p = y;
        if (y == null) {
            root = newNode; // tree was empty
        } else if (k < y.key) {
            y.left = newNode;
        } else {
            y.right = newNode;
        }
    }

    /**
     * Performs search of the element with key {@code k}.
     * <p>
     * Recursive search.
     *
     * @param root root of the tree
     * @param k    key to find
     * @return node with that key
     */
    private TreeNode search(TreeNode root, int k) {
        if (root == null || root.key == k) {
            return root;
        }
        if (k < root.key) {
            return search(root.left, k);
        } else {
            return search(root.right, k);
        }
    }

    /**
     * Performs search of the element with key {@code k}.
     * <p>
     * Recursive search.
     *
     * @param k key to find
     * @return node with that key
     */
    public TreeNode search(int k) {
        return search(root, k);
    }

    /**
     * Performs search of the element with key {@code k}.
     * <p>
     * Iterative search.
     *
     * @param k key to find
     * @return node with that key
     */
    public TreeNode searchIterative(int k) {
        TreeNode x = root;
        while (x != null && x.key != k) {
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }

    private TreeNode minimum(TreeNode root) {
        if (root.left == null) {
            return root;
        } else {
            return minimum(root.left);
        }
    }

    public TreeNode minimum() {
        return minimum(root);
    }

    private TreeNode minimumIterative(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public TreeNode minimumIterative() {
        return minimumIterative(root);
    }

    private TreeNode maximum(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public TreeNode maximum() {
        return maximum(root);
    }

    public TreeNode successor(TreeNode x) {
        if (x.right != null) {
            return minimum(x.right);
        }
        TreeNode y = x.p;
        while (y != null && x == y.right) {
            x = y;
            y = y.p;
        }
        return y;
    }
}
