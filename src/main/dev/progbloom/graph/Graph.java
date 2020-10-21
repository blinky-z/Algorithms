package dev.progbloom.graph;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Undirected graph representation.
 * <p>
 * Graph stored as Adjacency List. Adjacency list represented as {@code List<LIst<Integer>>}
 * so adding element takes {@code O(1)} time, but be aware of duplicate vertexes.
 * <p>
 * Last vertex index is V - 1, first vertex index is 0.
 */
public class Graph {

    private int V;

    private int E;

    /**
     * Adjacency list.
     * <p>
     * As last vertex index is V - 1, first vertex index is 0, so the capacity of this list is V.
     */
    private List<List<Integer>> adj;

    /**
     * Creates a graph wth no edges but with V vertexes.
     *
     * @param V number of vertexes
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.set(i, new ArrayList<>());
        }
    }

    /**
     * Creates a graph from input stream.
     * <p>
     * First value is number of vertexes.
     * Second value is number of edges (E).
     * <p>
     * Then E edges follows in the following format:
     * <pre>v w</pre>,
     * <p>
     * where v w represents edge.
     *
     * @param in input stream
     */
    public Graph(ObjectInputStream in) throws IOException {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    /**
     * Returns number of vertexes.
     *
     * @return number of vertexes.
     */
    public int V() {
        return V;
    }

    /**
     * Returns number of edges.
     *
     * @return number of edges
     */
    public int E() {
        return E;
    }

    /**
     * Add edge v-w.
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
        E++;
    }

    /**
     * Returns all adjacent vertexes of this node {@code v}.
     *
     * @param v node
     * @return adjacent vertexes of v
     */
    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(V + " вершин, " + E + " ребер\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : this.adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
