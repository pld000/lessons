package binary_search_tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTUtilsTest {

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

    BST<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BST<>(new BSTNode<>(80, 888, null));
    }

    @Test
    void invertTree() {
        makeTreeFilling();
        Assertions.assertEquals(10, bst.FinMinMax(bst.Root, false).NodeKey, "Min failed");
        Assertions.assertEquals(150, bst.FinMinMax(bst.Root, true).NodeKey, "Max failed");

        BSTUtils treeUtils = new BSTUtils();
        treeUtils.InvertTree(bst);

        Assertions.assertEquals(150, bst.FinMinMax(bst.Root, false).NodeKey, "Min failed");
        Assertions.assertEquals(10, bst.FinMinMax(bst.Root, true).NodeKey, "Max failed");
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