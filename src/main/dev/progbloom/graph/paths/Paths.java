package dev.progbloom.graph.paths;

import org.jetbrains.annotations.Nullable;

/**
 * Class for finding paths between vertexes.
 *
 * @implNote Pass graph and start node to constructor in implementation.
 */
public interface Paths {

    /**
     * Is there path from start vertex to vertex {@code v}.
     *
     * @param v destination vertex
     * @return {@code true} if path exists, {@code false} otherwise
     */
    boolean hasPathTo(int v);

    /**
     * Returns path from start vertex to vertex {@code v}.
     *
     * @param v destination vertex
     * @return path if exists, {@code null} otherwise
     */
    @Nullable
    Iterable<Integer> pathTo(int v);
}
