package simple_grapsh;

import java.util.*;

class Vertex {
    public int Value;

    public Vertex(int val) {
        Value = val;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        int slot = _getSlot();

        if (slot == -1) {
            return;
        }

        vertex[slot] = new Vertex(value);

        for (int[] edges : m_adjacency) {
            edges[slot] = 0;
        }
        Arrays.fill(m_adjacency[slot], 0);
    }

    public void RemoveVertex(int v) {
        if (v >= vertex.length) {
            return;
        }

        vertex[v] = null;

        for (int[] edges : m_adjacency) {
            edges[v] = 0;
        }

        Arrays.fill(m_adjacency[v], 0);
    }

    public boolean IsEdge(int v1, int v2) {
        if (v1 >= vertex.length || v2 >= vertex.length || vertex[v1] == null || vertex[v2] == null) {
            return false;
        }

        return m_adjacency[v1][v2] == 1 && m_adjacency[v2][v1] == 1;
    }

    public void AddEdge(int v1, int v2) {
        if (v1 >= vertex.length || v2 >= vertex.length || vertex[v1] == null || vertex[v2] == null) {
            return;
        }

        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        if (v1 >= vertex.length || v2 >= vertex.length || vertex[v1] == null || vertex[v2] == null) {
            return;
        }

        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    protected int _getSlot() {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null) {
                return i;
            }
        }

        return -1;
    }
}