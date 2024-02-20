package simple_graph;

import java.util.*;

class Vertex {
    public int Value;
    public boolean Hit;

    public int Slot;

    public Vertex(int val) {
        Value = val;
        Hit = false;
        Slot = -1;
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
        _ResetDfs();

        if (VFrom < vertex.length && VTo < vertex.length) {
            _DepthFirstSearch(VFrom, vertex[VTo].Value);
        }

        return pathStack;
    }

    public void AddVertex(int value) {
        int slot = _getSlot();

        if (slot == -1) {
            return;
        }

        vertex[slot] = new Vertex(value);
        vertex[slot].Slot = slot;

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



    public void _DepthFirstSearch(int vIndex, int VToValue) {
        Vertex pathVertex = vertex[vIndex];

        if (!pathVertex.Hit) {
            pathVertex.Hit = true;
            _Push(pathVertex);
        }

        if (pathVertex.Value == VToValue) {
            return;
        }

        for (int i = 0; i < m_adjacency[vIndex].length; i++) {
            boolean isAdjacent = m_adjacency[vIndex][i] == 1;
            if (isAdjacent && vertex[i].Value == VToValue) {
                vertex[i].Hit = true;
                _Push(vertex[i]);
                return;
            }
        }

        int nextIndex = _GetAdjacentNotHitIndex(vIndex);

        if (nextIndex == -1) {
            _Pop();

            if (pathStack.isEmpty()) {
                return;
            }

            nextIndex = _Last().Slot;
        }

        _DepthFirstSearch(nextIndex, VToValue);
    }

    private int _GetAdjacentNotHitIndex(int vIndex) {
        for (int i = 0; i < m_adjacency[vIndex].length; i++) {
            boolean isAdjacent = m_adjacency[vIndex][i] == 1;
            if (isAdjacent && !vertex[i].Hit) {
                return i;
            }
        }

        return -1;
    }

    private void _ResetDfs() {
        pathStack = new ArrayList<>();

        for (Vertex value : vertex) {
            value.Hit = false;
        }
    }


    private void _Push(Vertex v) {
        pathStack.add(v);
    }

    private Vertex _Last() {
        int size = pathStack.size();
        return size > 0 ? pathStack.get(size - 1) : null;
    }

    private Vertex _Pop() {
        int size = pathStack.size();
        return size > 0 ? pathStack.remove(size - 1) : null;
    }
}