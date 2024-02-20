package simple_graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SimpleGraphTest {
    public SimpleGraph graph;
    public int size = 5;

    public SimpleGraph graphDfs;
    public int sizeDfs = 10;

    @BeforeEach
    void setUp() {
        graph = new SimpleGraph(size);
        graphDfs = new SimpleGraph(sizeDfs);
    }

    @Test
    void depthFirstSearch() {
        int[] test = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90};

        for (int i : test) {
            graphDfs.AddVertex(i);
        }

        Assertions.assertEquals(0, graphDfs.DepthFirstSearch(999, 1000).size(), "Failed don't exist indexes");

        graphDfs.AddEdge(0, 1);
        graphDfs.AddEdge(1, 2);
        graphDfs.AddEdge(2, 3);
        graphDfs.AddEdge(3, 4);
        graphDfs.AddEdge(4, 7);
        graphDfs.AddEdge(7, 6);
        graphDfs.AddEdge(6, 9);
        ArrayList<Vertex> stack = graphDfs.DepthFirstSearch(2, 9);

        _printPathStack(stack);


    }

    private void _printPathStack(ArrayList<Vertex> stack) {
        for (int i = 0; i < stack.size(); i++) {
            String postfix = i == stack.size() - 1 ? "" : "->";
            System.out.print(stack.get(i).Value + postfix);
        }

        System.out.println();
        System.out.println("---------------------------");
    }

    @Test
    void addVertex() {
        int[] test = {5, 8, 2, 10, 1, 6, 9, 11};

        for (int i : test) {
            graph.AddVertex(i);
        }

        Assertions.assertEquals(size, graph.vertex.length, "Failed size after addVertex");

//        for (int i = 0; i < graph.vertex.length; i++) {
//            System.out.print(graph.vertex[i] + ", ");
//        }
    }

    @Test
    void removeVertex() {
        int[] test = {5, 8, 2, 10, 1};

        for (int i : test) {
            graph.AddVertex(i);
        }

        graph.RemoveVertex(99);
        graph.RemoveVertex(4);
        Assertions.assertEquals(4, graph._getSlot(), "Failed free slot after remove");

        graph.AddEdge(0, 4);
        Assertions.assertFalse(graph.IsEdge(0, 4), "Failed edge is exist for empty vertex");

        graph.AddEdge(0, 3);
        Assertions.assertTrue(graph.IsEdge(0, 3), "Failed edge for existing vertex");

        graph.RemoveVertex(3);
        Assertions.assertFalse(graph.IsEdge(0, 3), "Failed edge is exist for empty vertex after removing");

    }

    @Test
    void isEdge() {
        int[] test = {5, 8, 2, 10, 1};

        for (int i : test) {
            graph.AddVertex(i);
        }

        Assertions.assertFalse(graph.IsEdge(2, 2), "Failed not existing edge circle");
        graph.AddEdge(2, 2);
        Assertions.assertTrue(graph.IsEdge(2, 2), "Failed existing edge circle");
        graph.RemoveEdge(2, 2);
        Assertions.assertFalse(graph.IsEdge(2, 2), "Failed edge circle after removing");

        Assertions.assertFalse(graph.IsEdge(2, 4), "Failed not existing edge");
        graph.AddEdge(4, 2);
        Assertions.assertTrue(graph.IsEdge(2, 4), "Failed existing edge");
        graph.RemoveEdge(2, 4);
        Assertions.assertFalse(graph.IsEdge(4, 2), "Failed edge after removing");


    }

    @Test
    void addEdge() {
    }

    @Test
    void removeEdge() {
    }
}