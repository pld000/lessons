package trees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Assertions.assertNull(tree.FindNodesByValue(9999), "Failed searching in only tree with root");
        Assertions.assertEquals(tree.Root, tree.FindNodesByValue(99).get(0), "Failed searching in root object");
        Assertions.assertEquals(99, tree.FindNodesByValue(99).get(0).NodeValue, "Failed searching in root value");

        int length = 20;
        for (int i = 0; i < length; i++) {
            SimpleTreeNode<Integer> simpleNode = new SimpleTreeNode<>(i, null);
            tree.AddChild(tree.Root, simpleNode);

            for (int j = 0; j < length; j++) {
                SimpleTreeNode<Integer> simpleChildNode1 = new SimpleTreeNode<>(j, null);
                tree.AddChild(simpleNode, simpleChildNode1);

                for (int k = 0; k < length; k++) {
                    SimpleTreeNode<Integer> simpleChildNode2 = new SimpleTreeNode<>(k, null);
                    tree.AddChild(simpleChildNode1, simpleChildNode2);
                }
            }
        }
        List<SimpleTreeNode<Integer>> searchingNodes = tree.FindNodesByValue(7);
        Assertions.assertEquals(7, searchingNodes.get(0).NodeValue, "Failed searching in root value");
        Assertions.assertEquals(421, tree.FindNodesByValue(7).size(), "Failed searching in root value");

        for (int i = 0; i < searchingNodes.size(); i++) {
            Assertions.assertEquals(7, searchingNodes.get(i).NodeValue, "Failed 7 not equals");
        }
    }

    @Test
    void moveNode() {
    }

    @Test
    void count() {
        Assertions.assertEquals(1, tree.Count(), "Failed one root count");

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

        Assertions.assertEquals(41, tree.Count(), "Field count for many");
    }

    @Test
    void countForMuchMany() {
        int length = 20;
        for (int i = 0; i < length; i++) {
            SimpleTreeNode<Integer> simpleNode = new SimpleTreeNode<>(i, null);
            tree.AddChild(tree.Root, simpleNode);

            for (int j = 0; j < length; j++) {
                SimpleTreeNode<Integer> simpleChildNode1 = new SimpleTreeNode<>(j, null);
                tree.AddChild(simpleNode, simpleChildNode1);

                for (int k = 0; k < length; k++) {
                    SimpleTreeNode<Integer> simpleChildNode2 = new SimpleTreeNode<>(k, null);
                    tree.AddChild(simpleChildNode1, simpleChildNode2);
                }
            }
        }

        Assertions.assertEquals(8421, tree.Count(), "Failed countForMuchMany");
    }

    @Test
    void leafCount() {
        int length = 20;
        for (int i = 0; i < length; i++) {
            SimpleTreeNode<Integer> simpleNode = new SimpleTreeNode<>(i, null);
            tree.AddChild(tree.Root, simpleNode);

            for (int j = 0; j < length; j++) {
                SimpleTreeNode<Integer> simpleChildNode1 = new SimpleTreeNode<>(j, null);
                tree.AddChild(simpleNode, simpleChildNode1);

                for (int k = 0; k < length; k++) {
                    SimpleTreeNode<Integer> simpleChildNode2 = new SimpleTreeNode<>(k, null);
                    tree.AddChild(simpleChildNode1, simpleChildNode2);
                }
            }
        }

        Assertions.assertEquals(8000, tree.LeafCount(), "Failed LeafCountForMuchMany");
    }
}