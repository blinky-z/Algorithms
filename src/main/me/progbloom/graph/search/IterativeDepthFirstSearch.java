package me.progbloom.graph.search;

import me.progbloom.datastruct.stack.ArrayStack;
import me.progbloom.datastruct.stack.Stack;
import me.progbloom.graph.Graph;

/**
 * Итеративная имплементация DFS
 *
 * @author Dmitry Alexandrov (alexandrov@yoomoney.ru)
 * @since 10.03.2022
 */
public class IterativeDepthFirstSearch {

    private boolean[] marked;
    private int count;

    /**
     * Performs depth-first search (DFS) on the given graph on construct.
     *
     * @param G graph
     * @param s start vertex
     */
    public IterativeDepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int start) {
        Stack<Integer> stack = new ArrayStack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            Integer v = stack.pop();
            if (marked[v]) {
                continue;
            }
            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    stack.push(w);
                }
            }
        }
    }
}
