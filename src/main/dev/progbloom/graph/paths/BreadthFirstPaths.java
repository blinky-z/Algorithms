package dev.progbloom.graph.paths;

import dev.progbloom.graph.Graph;
import dev.progbloom.graph.GraphUtils;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This implementation finds paths using BFS, and moreover finds the shortest path.
 */
public class BreadthFirstPaths implements Paths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new LinkedList<>();
        marked[s] = true;
        q.add(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    q.add(w);
                }
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
