package dev.progbloom.graph;

import java.util.Stack;

/**
 * Some useful methods for graphs.
 */
public class GraphUtils {

    private GraphUtils() {
    }

    /**
     * Evaluates this vertex's degree (the number of edges that are incident to the vertex).
     *
     * @param g graph
     * @param v vertex
     * @return vertex's degree
     */
    public static int degree(Graph g, int v) {
        int degree = 0;
        for (int w : g.adj(v)) {
            degree++;
        }
        return degree;
    }

    /**
     * Max degree of all the vertexes.
     *
     * @param g graph
     * @return max degree
     */
    public static int maxDegree(Graph g) {
        int max = 0;
        for (int v = 0; v < g.V(); v++) {
            if (degree(g, v) > max) {
                max = degree(g, v);
            }
        }
        return max;
    }

    /**
     * Average graph degree.
     *
     * @param g graph
     * @return average degree
     */
    public static int avgDegree(Graph g) {
        return 2 * g.E() / g.V();
    }

    /**
     * Count self loops (an edge that connects a vertex to itself).
     *
     * @param g graph
     * @return number of self loops
     */
    public static int numberOfSelfLoops(Graph g) {
        int count = 0;
        for (int v = 0; v < g.V(); v++) {
            for (int w : g.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        return count / 2;
    }

    /**
     * Builds path. Path should exists to build it.
     *
     * @param s      start vertex
     * @param v      destination vertex
     * @param edgeTo array with last edge to every node
     * @return path from s to v
     */
    public static Iterable<Integer> buildPath(int s, int v, int[] edgeTo) {
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
