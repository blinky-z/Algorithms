package dev.progbloom.graph.paths;

import dev.progbloom.graph.Graph;
import dev.progbloom.graph.GraphUtils;
import org.jetbrains.annotations.Nullable;

/**
 * This implementation finds paths using DFS.
 */
public class DepthFirstPaths implements Paths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Is there path from start vertex to vertex {@code v}.
     *
     * @param v destination vertex
     * @return {@code true} if path exists, {@code false} otherwise
     */
    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Returns path from start vertex to vertex {@code v}.
     *
     * @param v destination vertex
     * @return path if exists, {@code null} otherwise
     */
    @Override
    @Nullable
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        return GraphUtils.buildPath(s, v, edgeTo);
    }
}
