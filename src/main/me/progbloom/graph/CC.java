package me.progbloom.graph;

/**
 * Finds connected components in undirected graph. Also assigns component ID to every vertex.
 */
public class CC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * Is there path between vertex {@code v} and vertex {@code w}.
     *
     * @param v first vertex
     * @param w second vertex
     * @return {@code true} if path exists, {@code false} otherwise
     */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * Returns ID of connected component in which the given vertex in. Values may vary from {@code 0} to {@code count - 1}.
     *
     * @param v vertex
     * @return connected component ID
     * @see #count
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * Returns number of connected components.
     *
     * @return number of connected components
     */
    public int count() {
        return count;
    }
}
