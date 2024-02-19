package simple_graph;

import java.util.*;

class Vertex {
    public int Value;
    public boolean Hit;

    public Vertex(int val) {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    ArrayList<Vertex> pathStack;
    int[][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        pathStack = new ArrayList<>();

        for (int i = 0; i < vertex.length; i++) {
            vertex[i].Hit = false;
        }

        if (VFrom < vertex.length && VTo < vertex.length) {
            _DepthFirstSearch(VFrom, vertex[VTo].Value);
        }

        return pathStack;
    }

    public void _DepthFirstSearch(int vIndex, int VToValue) {
        Vertex pathVertex = vertex[vIndex];
        pathVertex.Hit = true;
        push(pathVertex);

        if (pathVertex.Value == VToValue) {
            return;
        }

        for (int i =0; i< m_adjacency[vIndex].length; i++) {
            boolean isAdjacent = m_adjacency[vIndex][i] == 1;
            if (isAdjacent && !vertex[i].Hit) {
                _DepthFirstSearch(i, VToValue);
            }
        }
    }

    public void push(Vertex v) {
        pathStack.add(v);
    }

    public Vertex pop() {
        int size = pathStack.size();
        return size > 0 ? pathStack.get(size - 1) : null;
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