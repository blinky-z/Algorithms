package me.progbloom.graph;

import java.util.Stack;

/**
 * Topological sort for DAGs (directed acyclic graphs).
 */
public class TopologicalSort {

    private boolean[] marked;
    private Stack<Integer> ans;

    TopologicalSort(Graph G) {
        marked = new boolean[G.V()];
        ans = new Stack<>();
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        ans.push(v);
    }

    private Iterable<Integer> getTopologicalSort() {
        return ans;
    }
}
