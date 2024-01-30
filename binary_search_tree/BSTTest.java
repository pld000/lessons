package binary_search_tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    BSTNode<Integer> node1 = new BSTNode<>(10, 111, null);
    BSTNode<Integer> node2 = new BSTNode<>(20, 222, null);
    BSTNode<Integer> node3 = new BSTNode<>(30, 333, null);
    BSTNode<Integer> node4 = new BSTNode<>(40, 444, null);
    BSTNode<Integer> node5 = new BSTNode<>(50, 555, null);
    BSTNode<Integer> node6 = new BSTNode<>(60, 666, null);
    BSTNode<Integer> node7 = new BSTNode<>(70, 777, null);
    // BSTNode<Integer> node8 = new BSTNode<>(80, 888, null); // because it is Root
    BSTNode<Integer> node9 = new BSTNode<>(90, 999, null);
    BSTNode<Integer> node10 = new BSTNode<>(100, 101010, null);
    BSTNode<Integer> node11 = new BSTNode<>(110, 111111, null);
    BSTNode<Integer> node12 = new BSTNode<>(120, 121212, null);
    BSTNode<Integer> node13 = new BSTNode<>(130, 131313, null);
    BSTNode<Integer> node14 = new BSTNode<>(140, 141414, null);
    BSTNode<Integer> node15 = new BSTNode<>(150, 151515, null);

    BST<Integer> bstNull;
    BST<Integer> bst;

    @BeforeEach
    void setUp() {
        bstNull = new BST<>(null);
        bst = new BST<>(new BSTNode<>(80, 888, null));
    }

    @Test
    void findNodeByKeyForEmpty() {
        Assertions.assertNull(bstNull.FindNodeByKey(40).Node, "Failed findNodeByKeyForEmpty for empty tree");

        Assertions.assertEquals(bst.Root, bst.FindNodeByKey(80).Node, "Failed findNodeByKeyForEmpty for root");
        Assertions.assertTrue(bst.FindNodeByKey(80).NodeHasKey, "Failed findNodeByKeyForEmpty for root true");

        Assertions.assertEquals(bst.Root, bst.FindNodeByKey(90).Node, "Failed findNodeByKeyForEmpty for root when key not found");
        Assertions.assertFalse(bst.FindNodeByKey(90).NodeHasKey, "Failed findNodeByKeyForEmpty for root false");
        Assertions.assertFalse(bst.FindNodeByKey(90).ToLeft, "Failed findNodeByKeyForEmpty for root false");

        Assertions.assertEquals(bst.Root, bst.FindNodeByKey(60).Node, "Failed findNodeByKeyForEmpty for root when key not found");
        Assertions.assertFalse(bst.FindNodeByKey(60).NodeHasKey, "Failed findNodeByKeyForEmpty for root false");
        Assertions.assertTrue(bst.FindNodeByKey(60).ToLeft, "Failed findNodeByKeyForEmpty for root false");
    }

    @Test
    void findNodeByKeyAfterFill() {
        makeTreeFilling();

        BSTFind<Integer> rightChildNotFound = bst.FindNodeByKey(39);
        Assertions.assertEquals(node3.NodeValue, rightChildNotFound.Node.NodeValue, "Failed rightChildNotFound node");
        Assertions.assertFalse(rightChildNotFound.NodeHasKey, "Failed rightChildNotFound NodeHasKey");
        Assertions.assertFalse(rightChildNotFound.ToLeft, "Failed rightChildNotFound ToLeft");

        BSTFind<Integer> leftChildNotFound = bst.FindNodeByKey(45);
        Assertions.assertEquals(node5.NodeValue, leftChildNotFound.Node.NodeValue, "Failed leftChildNotFound node");
        Assertions.assertFalse(leftChildNotFound.NodeHasKey, "Failed leftChildNotFound NodeHasKey");
        Assertions.assertTrue(leftChildNotFound.ToLeft, "Failed leftChildNotFound ToLeft");


        BSTFind<Integer> rightChildFound = bst.FindNodeByKey(30);
        Assertions.assertEquals(node3.NodeValue, rightChildFound.Node.NodeValue, "Failed rightChildFound node");
        Assertions.assertTrue(rightChildFound.NodeHasKey, "Failed rightChildFound NodeHasKey");

        BSTFind<Integer> leftChildFound = bst.FindNodeByKey(100);
        Assertions.assertEquals(node10.NodeValue, leftChildFound.Node.NodeValue, "Failed leftChildFound node");
        Assertions.assertTrue(leftChildFound.NodeHasKey, "Failed leftChildFound NodeHasKey");

    }

    @Test
    void addKeyValue() {
        Assertions.assertNull(bstNull.FindNodeByKey(10).Node, "Failed addKeyValue to empty tree before");
        Assertions.assertTrue(bstNull.AddKeyValue(10, 100), "Failed addKeyValue to root");
        Assertions.assertEquals(10, bstNull.Root.NodeKey, "Failed addKeyValue to empty tree after");
        Assertions.assertEquals(10, bstNull.FindNodeByKey(10).Node.NodeKey, "Failed addKeyValue to empty tree after");

        makeTreeFilling();

        Assertions.assertFalse(bst.AddKeyValue(60, 600), "Failed addKeyValue when key exist");

        Assertions.assertFalse(bst.FindNodeByKey(45).NodeHasKey, "Failed addKeyValue find key");
        Assertions.assertTrue(bst.AddKeyValue(45, 450), "Failed addKeyValue new key to left child");
        Assertions.assertTrue(bst.FindNodeByKey(45).NodeHasKey, "Failed search after addKeyValue to left child");
        Assertions.assertEquals(node5.LeftChild.NodeKey, bst.FindNodeByKey(45).Node.NodeKey, "Failed LeftChild check");

        Assertions.assertFalse(bst.FindNodeByKey(160).NodeHasKey, "Failed addKeyValue find key");
        Assertions.assertTrue(bst.AddKeyValue(160, 1600), "Failed addKeyValue new key to right child");
        Assertions.assertTrue(bst.FindNodeByKey(160).NodeHasKey, "Failed search after addKeyValue to right child");
        Assertions.assertEquals(node15.RightChild.NodeKey, bst.FindNodeByKey(160).Node.NodeKey, "Failed RightChild check");
    }

    @Test
    void finMinMax() {
        Assertions.assertNull(bstNull.FinMinMax(bstNull.Root, true), "Failed finMinMax empty");

        makeTreeFilling();

        Assertions.assertEquals(10, bst.FinMinMax(bst.Root, false).NodeKey, "Failed root finMinMax min");
        Assertions.assertEquals(150, bst.FinMinMax(bst.Root, true).NodeKey, "Failed root finMinMax max");

        Assertions.assertEquals(90, bst.FinMinMax(node12, false).NodeKey, "Failed for node finMinMax min");
        Assertions.assertEquals(150, bst.FinMinMax(node12, true).NodeKey, "Failed for node finMinMax max");
    }

    @Test
    void deleteNodeByKey() {
        Assertions.assertFalse(bstNull.DeleteNodeByKey(23), "Failed empty delete");
        bstNull.AddKeyValue(50, 500);
        Assertions.assertTrue(bstNull.DeleteNodeByKey(50), "Failed empty delete after adding");
        Assertions.assertNull(bstNull.Root, "Failed empty root after delete");

        bstNull.AddKeyValue(50, 500);
        bstNull.AddKeyValue(30, 500);
        bstNull.AddKeyValue(70, 500);
        Assertions.assertTrue(bstNull.DeleteNodeByKey(50), "Failed empty delete after adding");
        Assertions.assertEquals(70, bstNull.Root.NodeKey, "Failed empty delete root after three elements adding");
        Assertions.assertFalse(bstNull.FindNodeByKey(50).NodeHasKey, "Failed search after delete");

        makeTreeFilling();

        Assertions.assertFalse(bst.DeleteNodeByKey(9999), "Failed delete absent key");
        Assertions.assertTrue(bst.DeleteNodeByKey(node6.NodeKey), "Failed delete node6");
        Assertions.assertFalse(bst.FindNodeByKey(node6.NodeKey).NodeHasKey, "Failed search node6 after delete");
        Assertions.assertEquals(node5.NodeKey, bst.FindNodeByKey(node7.NodeKey).Node.LeftChild.NodeKey, "Failed node7 LeftChild replace after delete");
        Assertions.assertEquals(node4.RightChild.NodeKey, bst.FindNodeByKey(node7.NodeKey).Node.NodeKey, "Failed node7 parent replace after delete");
        Assertions.assertTrue(bst.DeleteNodeByKey(node15.NodeKey), "Failed delete node15");
        Assertions.assertFalse(bst.FindNodeByKey(node15.NodeKey).NodeHasKey, "Failed search node15 after delete");
        Assertions.assertNull(bst.FindNodeByKey(node14.NodeKey).Node.RightChild, "Failed node 14 right child");

        Assertions.assertTrue(bst.DeleteNodeByKey(node14.NodeKey), "Failed delete node14");
        Assertions.assertFalse(bst.FindNodeByKey(node14.NodeKey).NodeHasKey, "Failed search node14 after delete");
        Assertions.assertEquals(node12.NodeKey, bst.FindNodeByKey(node13.NodeKey).Node.Parent.NodeKey, "Failed parent for node 13");
        Assertions.assertEquals(node12.RightChild.NodeKey, bst.FindNodeByKey(node13.NodeKey).Node.NodeKey, "Failed right child for node 12");

        Assertions.assertTrue(bst.DeleteNodeByKey(bst.Root.NodeKey), "Failed root delete");

        Assertions.assertEquals(node9.NodeKey, bst.Root.NodeKey);
        Assertions.assertNull(bst.FindNodeByKey(node10.NodeKey).Node.LeftChild, "Failed node10 left child");
        Assertions.assertEquals(node4.NodeKey, bst.Root.LeftChild.NodeKey, "Failed root left child");
        Assertions.assertEquals(node12.NodeKey, bst.Root.RightChild.NodeKey, "Failed root right child");

    }

    @Test
    void count() {
    }

    public void makeTreeFilling() {

        // Level - 1
        bst.Root.LeftChild = node4;
        node4.Parent = bst.Root;

        bst.Root.RightChild = node12;
        node12.Parent = bst.Root;

        // Level - 2
        node4.LeftChild = node2;
        node4.RightChild = node6;

        node2.Parent = node4;
        node6.Parent = node4;

        node12.LeftChild = node10;
        node12.RightChild = node14;

        node10.Parent = node12;
        node14.Parent = node12;

        // Level - 3
        node2.LeftChild = node1;
        node2.RightChild = node3;

        node1.Parent = node2;
        node3.Parent = node2;

        node6.LeftChild = node5;
        node6.RightChild = node7;

        node5.Parent = node6;
        node7.Parent = node6;


        node10.LeftChild = node9;
        node10.RightChild = node11;

        node9.Parent = node10;
        node11.Parent = node10;

        node14.LeftChild = node13;
        node14.RightChild = node15;

        node13.Parent = node14;
        node15.Parent = node14;
    }
}