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
        tree.AddChild(null, firstChild);

        SimpleTreeNode<Integer> secondChild = new SimpleTreeNode<>(12, null);
        tree.AddChild(firstChild, secondChild);

        SimpleTreeNode<Integer> thirdChild = new SimpleTreeNode<>(13, null);
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
        Assertions.assertEquals(1, tree.GetAllNodes().size(), "Field getAllNodes for one root node");

        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(11, null);
        tree.AddChild(tree.Root, node1);
        Assertions.assertEquals(2, tree.GetAllNodes().size(), "Field getAllNodes for one");
    }

    @Test
    void getAllNodesForMany() {
        for (int i = 0; i < 10; i++) {
            SimpleTreeNode<Integer> simpleNode = new SimpleTreeNode<>(i, null);
            tree.AddChild(tree.Root, simpleNode);

            SimpleTreeNode<Integer> simpleChildNode1 = new SimpleTreeNode<>(100 + i, null);
            tree.AddChild(simpleNode, simpleChildNode1);

            SimpleTreeNode<Integer> simpleChildNode2 = new SimpleTreeNode<>(1000 + i, null);
            tree.AddChild(simpleChildNode1, simpleChildNode2);

            SimpleTreeNode<Integer> simpleChildNode3 = new SimpleTreeNode<>(10000 + i, null);
            tree.AddChild(simpleChildNode1, simpleChildNode3);
        }

        Assertions.assertEquals(41, tree.GetAllNodes().size(), "Field getAllNodesForMany");
    }

    @Test
    void getAllNodesForManyAfterDelete() {
        int length = 5;
        for (int i = 0; i < length; i++) {
            SimpleTreeNode<Integer> simpleNode = new SimpleTreeNode<>(i, null);
            tree.AddChild(tree.Root, simpleNode);

            for (int j = 0; j < length; j++) {
                SimpleTreeNode<Integer> simpleChildNode1 = new SimpleTreeNode<>(100 + j, null);
                tree.AddChild(simpleNode, simpleChildNode1);

                for (int k = 0; k < length; k++) {
                    SimpleTreeNode<Integer> simpleChildNode2 = new SimpleTreeNode<>(1000 + k, null);
                    tree.AddChild(simpleChildNode1, simpleChildNode2);
                }
            }
        }
        Assertions.assertEquals(156, tree.GetAllNodes().size(), "Field adding nodes for getAllNodesForManyAfterDelete");

        SimpleTreeNode<Integer> nodeForDelete = tree.Root.Children.get(4);
        tree.DeleteNode(nodeForDelete);
        Assertions.assertEquals(125, tree.GetAllNodes().size(), "Field getAllNodesForManyAfterDelete");

        SimpleTreeNode<Integer> anotherForDelete = tree.Root.Children.get(3).Children.get(3);
        tree.DeleteNode(anotherForDelete);
        Assertions.assertEquals(119, tree.GetAllNodes().size(), "Field getAllNodesForManyAfterDelete");

        tree.DeleteNode(tree.Root.Children.get(3));
        tree.DeleteNode(tree.Root.Children.get(2));
        tree.DeleteNode(tree.Root.Children.get(1));
        tree.DeleteNode(tree.Root.Children.get(0));
        Assertions.assertEquals(1, tree.GetAllNodes().size(), "Field getAllNodesForManyAfterDelete");
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