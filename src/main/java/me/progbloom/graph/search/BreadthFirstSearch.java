package me.progbloom.graph.search;

import me.progbloom.collection.queue.LinkedListQueue;
import me.progbloom.collection.queue.Queue;
import me.progbloom.graph.Graph;

/**
 * Breadth first search algorithm.
 */
public class BreadthFirstSearch {

    private boolean[] marked;

    public BreadthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int root) {
        Queue<Integer> q = new LinkedListQueue<>();
        marked[root] = true;
        q.add(root);
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
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
}
