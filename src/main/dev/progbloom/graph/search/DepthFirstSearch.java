package dev.progbloom.graph.search;

import dev.progbloom.graph.Graph;

/**
 * Depth first search algorithm.
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

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
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
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

    /**
     * How many vertexes was visited.
     *
     * @return number of vertexes visited
     */
    public int count() {
        return count;
    }
}
