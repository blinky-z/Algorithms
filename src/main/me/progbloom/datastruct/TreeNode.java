package me.progbloom.datastruct;

/**
 * Tree node representation.
 */
public class TreeNode {

    int key;

    TreeNode left;

    TreeNode right;

    TreeNode p;

    public TreeNode(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
            "key=" + key +
            '}';
    }
}
