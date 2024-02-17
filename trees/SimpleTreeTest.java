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
    public SimpleTree<Integer> nullTree;

    @BeforeEach
    void setUp() {
        tree = new SimpleTree<>(new SimpleTreeNode<>(99, null));
        nullTree = new SimpleTree<>(null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void EvenTrees() {
        Assertions.assertEquals(0, nullTree.EvenTrees().size(), "Failed null even tree");
        Assertions.assertEquals(0, tree.EvenTrees().size(), "Failed root only even tree");

        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(11, null);
        tree.AddChild(tree.Root, node1);
        Assertions.assertEquals(0, tree.EvenTrees().size(), "Failed two elements even tree");

        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(25, null);
        tree.AddChild(tree.Root, node2);
        Assertions.assertEquals(0, tree.EvenTrees().size(), "Failed three elements even tree");

        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(35, null);
        tree.AddChild(tree.Root, node3);
        Assertions.assertEquals(0, tree.EvenTrees().size(), "Failed four elements even tree");
    }

    @Test
    void EvenTrees2() {
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(10, null);
        tree.AddChild(tree.Root, node1);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(20, null);
        tree.AddChild(node1, node2);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(30, null);
        tree.AddChild(node2, node3);


        ArrayList<Integer> forest = tree.EvenTrees();
        Assertions.assertEquals(2, forest.size(), "Failed four elements even tree");

        for (int i = 0; i < forest.size(); i++) {
            System.out.print(forest.get(i) + ", ");

            if (i % 2 > 0) {
                System.out.println();
            }
        }
    }

    @Test
    void EvenTrees3() {
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(10, null);
        tree.AddChild(tree.Root, node1);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(20, null);
        tree.AddChild(node1, node2);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(30, null);
        tree.AddChild(node2, node3);

        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(40, null);
        tree.AddChild(node1, node4);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(50, null);
        tree.AddChild(node4, node5);

        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(60, null);
        tree.AddChild(node5, node6);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(70, null);
        tree.AddChild(node6, node7);

        ArrayList<Integer> forest = tree.EvenTrees();
        Assertions.assertEquals(6, forest.size(), "Failed four elements even tree");

        for (int i = 0; i < forest.size(); i++) {
            System.out.print(forest.get(i) + ", ");

            if (i % 2 > 0) {
                System.out.println();
            }
        }
    }
    @Test
    void EvenTrees4() {
        SimpleTree<Integer> testTree = new SimpleTree<>(new SimpleTreeNode<>(1, null));

        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
        testTree.AddChild(testTree.Root, node2);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
        testTree.AddChild(testTree.Root, node3);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
        testTree.AddChild(testTree.Root, node6);

        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
        testTree.AddChild(node2, node5);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
        testTree.AddChild(node2, node7);

        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
        testTree.AddChild(node3, node4);

        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(8, null);
        testTree.AddChild(node6, node8);

        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(9, null);
        testTree.AddChild(node8, node9);
        SimpleTreeNode<Integer> node10 = new SimpleTreeNode<>(10, null);
        testTree.AddChild(node8, node10);

        ArrayList<Integer> forest = testTree.EvenTrees();
        Assertions.assertEquals(4, forest.size(), "Failed four elements even tree");

        for (int i = 0; i < forest.size(); i++) {
            System.out.print(forest.get(i) + ", ");

            if (i % 2 > 0) {
                System.out.println();
            }
        }
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
        Assertions.assertNull(tree.Root.Children, "Failed after delete last child from parent");
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
        int length = 5;
        for (int i = 0; i < length; i++) {
            SimpleTreeNode<Integer> secondLevelNode = new SimpleTreeNode<>(21 + i, null);
            tree.AddChild(tree.Root, secondLevelNode);

            for (int j = 0; j < length; j++) {
                SimpleTreeNode<Integer> thirdLevelNode = new SimpleTreeNode<>(31 + j, null);
                tree.AddChild(secondLevelNode, thirdLevelNode);

                for (int k = 0; k < length; k++) {
                    SimpleTreeNode<Integer> fourthLevelNode = new SimpleTreeNode<>(41 + k, null);
                    tree.AddChild(thirdLevelNode, fourthLevelNode);
                }
            }
        }

        SimpleTreeNode<Integer> secondLevelParent = tree.Root.Children.get(2);
        SimpleTreeNode<Integer> fourthLevelToSecond = tree.Root.Children.get(4).Children.get(0).Children.get(0);
        SimpleTreeNode<Integer> oldParent = tree.Root.Children.get(4).Children.get(0);

        tree.MoveNode(fourthLevelToSecond, secondLevelParent);
        Assertions.assertEquals(6, fourthLevelToSecond.Parent.Children.size(), "Failed parent new children size");
        Assertions.assertEquals(6, secondLevelParent.Children.size(), "Failed parent new children size");
        Assertions.assertEquals(4, oldParent.Children.size(), "Failed parent new children size");
        Assertions.assertEquals(fourthLevelToSecond.NodeValue, secondLevelParent.Children.get(5).NodeValue);

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
        Assertions.assertEquals(1, tree.LeafCount(), "Failed empty tree");

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

        tree.DeleteNode(tree.Root.Children.get(19).Children.get(19).Children.get(19));
        Assertions.assertEquals(7999, tree.LeafCount(), "Failed LeafCountForMuchMany after one leaf delete");

        tree.DeleteNode(tree.Root.Children.get(15).Children.get(15));
        Assertions.assertEquals(7979, tree.LeafCount(), "Failed LeafCountForMuchMany after parent leaf delete");
    }

    @Test
    void leafCountOthers() {
        int length = 5;
        SimpleTreeNode<Integer> secondRoot = new SimpleTreeNode<>(999, tree.Root);
        tree.AddChild(tree.Root, secondRoot);
        for (int i = 0; i < length; i++) {
            SimpleTreeNode<Integer> simpleNode = new SimpleTreeNode<>(i, null);
            tree.AddChild(secondRoot, simpleNode);

            for (int j = 0; j < length; j++) {
                SimpleTreeNode<Integer> simpleChildNode1 = new SimpleTreeNode<>(j, null);
                tree.AddChild(simpleNode, simpleChildNode1);

                for (int k = 0; k < length; k++) {
                    SimpleTreeNode<Integer> simpleChildNode2 = new SimpleTreeNode<>(k, null);
                    tree.AddChild(simpleChildNode1, simpleChildNode2);
                }
            }
        }

        Assertions.assertEquals(125, tree.LeafCount(), "Failed leaf count");
        tree.DeleteNode(secondRoot);
        Assertions.assertEquals(1, tree.LeafCount(), "Failed leaf count");

    }

    @Test
    void leafCountOneLeaf() {
        Assertions.assertEquals(1, tree.LeafCount(), "Failed leaf count");
        SimpleTreeNode<Integer> secondRoot = new SimpleTreeNode<>(777, tree.Root);
        tree.AddChild(tree.Root, secondRoot);
        Assertions.assertEquals(1, tree.LeafCount(), "Failed leaf count after adding child");

        tree.DeleteNode(secondRoot);

        Assertions.assertEquals(1, tree.LeafCount(), "Failed leaf count after delete");
    }
}