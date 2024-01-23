package trees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {
    public SimpleTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new SimpleTree<>(new SimpleTreeNode<>(99, null));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addChild() {
        SimpleTreeNode<Integer> firstChild = new SimpleTreeNode<>(11, null);
        tree.AddChild(null, firstChild);
        Assertions.assertEquals(firstChild.NodeValue, tree.Root.Children.get(0).NodeValue, "Failed first child to root");


        SimpleTreeNode<Integer> secondChild = new SimpleTreeNode<>(33, null);
        tree.AddChild(firstChild, secondChild);
        Assertions.assertEquals(secondChild.NodeValue, firstChild.Children.get(0).NodeValue, "Failed secondChild to firstChild");
    }

    @Test
    void deleteNode() {
        SimpleTreeNode<Integer> firstChild = new SimpleTreeNode<>(11, null);
        SimpleTreeNode<Integer> secondChild = new SimpleTreeNode<>(12, null);
        SimpleTreeNode<Integer> thirdChild = new SimpleTreeNode<>(13, null);
        tree.AddChild(null, firstChild);
        tree.AddChild(firstChild, secondChild);
        tree.AddChild(firstChild, thirdChild);
        Assertions.assertEquals(1, tree.Root.Children.size(), "Failed add child");
        tree.DeleteNode(firstChild);
        Assertions.assertEquals(0, tree.Root.Children.size(), "Failed after delete from parent");
        Assertions.assertNull(firstChild.Children, "Failed removed children");


//        SimpleTreeNode<Integer> secondChild = new SimpleTreeNode<>(33, null);
//        tree.AddChild(firstChild, secondChild);
//        Assertions.assertEquals(secondChild.NodeValue, firstChild.Children.get(0).NodeValue, "Failed secondChild to firstChild");

    }

    @Test
    void getAllNodes() {
    }

    @Test
    void findNodesByValue() {
    }

    @Test
    void moveNode() {
    }

    @Test
    void count() {
    }

    @Test
    void leafCount() {
    }
}