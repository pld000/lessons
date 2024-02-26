package simple_graph;

import java.util.*;

class Vertex {
    public int Value;
    public boolean Hit;

    public int Slot;
    public Vertex prevPath;

    public Vertex(int val) {
        Value = val;
        Hit = false;
        Slot = -1;
        prevPath = null;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    ArrayList<Vertex> pathStack;
    ArrayList<Vertex> queue;
    int[][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public ArrayList<Vertex> WeakVertices() {
        ArrayList<Vertex> weakVertex = new ArrayList<>();

        if (_isEmpty()) {
            return weakVertex;
        }

        for (int i = 0; i < vertex.length; i++) {
            if (_isOutOfTriangle(i)) {
                weakVertex.add(vertex[i]);
            }
        }

        return weakVertex;
    }

    private boolean _isOutOfTriangle(int vInd) {
        ArrayList<Integer> adjacencyIndexes = _getAdjacencyIndexes(vInd);

        if (adjacencyIndexes.size() < 2) {
            return true;
        }

        for (int i = 0; i < adjacencyIndexes.size(); i++) {
            int nextInd = i + 1;
            if (nextInd < adjacencyIndexes.size()) {
                List<Integer> subList = adjacencyIndexes.subList(nextInd, adjacencyIndexes.size() - 1);

                for (int j = 0; j < subList.size(); j++) {
                    if (m_adjacency[j][adjacencyIndexes.get(i)] == 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private ArrayList<Integer> _getAdjacencyIndexes(int vInd) {
        ArrayList<Integer> adjacencyIndexes = new ArrayList<>();

        for (int i = 0; i < m_adjacency[vInd].length; i++) {
            if (vInd != i && m_adjacency[vInd][i] == 1) {
                adjacencyIndexes.add(i);
            }
        }

        return adjacencyIndexes;
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        _ResetVertex();
        queue = new ArrayList<>();
        ArrayList<Vertex> path = new ArrayList<>();
        Vertex searchingVertex = null;

        if (VFrom < vertex.length && VTo < vertex.length) {
            vertex[VFrom].Hit = true;
            vertex[VFrom].prevPath = null;
            _Enqueue(vertex[VFrom]);
            searchingVertex = _BreadthFirstSearch(vertex[VTo].Value);
        }

        return searchingVertex == null ? path : _MakePath(path, searchingVertex);
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        pathStack = new ArrayList<>();
        _ResetVertex();

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

    private boolean _isEmpty() {
        for (Vertex value : vertex) {
            if (value != null) {
                return false;
            }
        }

        return true;
    }


    private ArrayList<Vertex> _MakePath(ArrayList<Vertex> path, Vertex v) {
        if (v == null) {
            Collections.reverse(path);
            return path;
        }

        path.add(v);
        return _MakePath(path, v.prevPath);
    }

    private Vertex _BreadthFirstSearch(int VToValue) {
        if (queue.isEmpty()) {
            return null;
        }

        Vertex currentVertex = _Dequeue();

        if (currentVertex.Value == VToValue) {
            return currentVertex;
        }

        int[] adjacency = m_adjacency[currentVertex.Slot];
        for (int i = 0; i < adjacency.length; i++) {
            if (adjacency[i] == 1 && !vertex[i].Hit) {
                vertex[i].Hit = true;
                vertex[i].prevPath = currentVertex;
                _Enqueue(vertex[i]);

                if (vertex[i].Value == VToValue) {
                    return vertex[i];
                }
            }
        }

        return _BreadthFirstSearch(VToValue);
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

        int nextIndex = _GetAdjacentNotHitIndex(vIndex);

        if (nextIndex >= 0) {
            vertex[nextIndex].Hit = true;
            _Push(vertex[nextIndex]);

            if (vertex[nextIndex].Value == VToValue) {
                return;
            }
        } else {
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

    private void _ResetVertex() {
        for (Vertex v : vertex) {
            v.Hit = false;
            v.prevPath = null;
        }
    }

    private void _Enqueue(Vertex v) {
        queue.add(v);
    }

    private Vertex _Dequeue() {
        int size = queue.size();
        return size > 0 ? queue.remove(0) : null;
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