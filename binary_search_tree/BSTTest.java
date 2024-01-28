package binary_search_tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    BST<Integer> bstNull;
    BST<Integer> bst;

    @BeforeEach
    void setUp() {
        bstNull = new BST<>(null);
        bst = new BST<>(new BSTNode<>(8, 999, null));
    }

    @Test
    void findNodeByKey() {
        Assertions.assertNull(bstNull.FindNodeByKey(40).Node, "Failed findNodeByKey for empty tree");

        Assertions.assertEquals(bst.Root, bst.FindNodeByKey(8).Node, "Failed findNodeByKey for root");
        Assertions.assertTrue(bst.FindNodeByKey(8).NodeHasKey, "Failed findNodeByKey for root true");
        Assertions.assertEquals(bst.Root, bst.FindNodeByKey(80).Node, "Failed findNodeByKey for root when key not found");
        Assertions.assertFalse(bst.FindNodeByKey(80).NodeHasKey, "Failed findNodeByKey for root false");

        BSTNode<Integer> node4 = new BSTNode<>(4, 999, null);
        BSTNode<Integer> node2 = new BSTNode<>(2, 999, null);
        BSTNode<Integer> node6 = new BSTNode<>(6, 999, null);
        BSTNode<Integer> node10 = new BSTNode<>(10, 999, null);
        BSTNode<Integer> node12 = new BSTNode<>(12, 999, null);
        BSTNode<Integer> node14 = new BSTNode<>(14, 999, null);

        bst.Root.LeftChild = node4;
        node4.Parent = bst.Root;

        node4.LeftChild = node2;
        node4.RightChild = node6;

        node2.Parent = node4;
        node6.Parent = node4;

        bst.Root.RightChild = node12;
        node12.Parent = bst.Root;

        node12.LeftChild = node10;
        node12.RightChild = node14;

        node10.Parent = node12;
        node14.Parent = node12;
    }

    @Test
    void addKeyValue() {
    }

    @Test
    void finMinMax() {
    }

    @Test
    void deleteNodeByKey() {
    }

    @Test
    void count() {
    }
}