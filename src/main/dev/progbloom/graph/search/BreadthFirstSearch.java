package dev.progbloom.graph.search;

import dev.progbloom.graph.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Breadth first search algorithm.
 */
public class BreadthFirstSearch {
    private boolean[] marked;
    private int count;

    public BreadthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        count = 0;
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
                    count++;
                    q.add(w);
                }
            }
        }
    }

    /**
     * Was vertex {@code w} visited while bfs.
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
