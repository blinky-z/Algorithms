package me.progbloom.graph.search;

import me.progbloom.graph.Graph;

/**
 * Depth first search algorithm.
 */
public class DepthFirstSearch {

    private boolean[] marked;

    /**
     * Performs depth-first search (DFS) on the given graph on construct.
     *
     * @param G graph
     * @param s start vertex
     */
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        // we need to mark nodes as visited when:
        // 1. graph may contain cycles
        // 2. graph is undirected
        // otherwise it's not necessary
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * Was vertex {@code w} visited while dfs.
     *
     * @param w destination vertex
     * @return {@code true} if vertex was visited, {@code false} otherwise
     */
    public boolean marked(int w) {
        return marked[w];
    }
}
