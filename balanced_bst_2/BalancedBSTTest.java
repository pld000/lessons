package balanced_bst_2;

import binary_search_tree.BST;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BalancedBSTTest {
    public BalancedBST bst;

    @BeforeEach
    void setUp() {
        bst = new BalancedBST();
    }

    @Test
    void generateTree() {
//        int[] test2 = {140, 20, 30, 150, 50, 60, 70, 90, 80, 100, 110, 120, 130, 500};
        //  int[] test2 = {140, 20, 30, 150, 50, 60, 70, 90, 80, 100, 110, 120, 130, 500};
        int[] test2 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
        bst.GenerateTree(test2);

        printBst(bst);
    }

    @Test
    void isBalanced() {
        int[] test2 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};

        bst.GenerateTree(test2);
        BSTNode node = getNodeByKey(bst.Root, 150);

        node.RightChild = new BSTNode(160, node);
        node.RightChild.RightChild = new BSTNode(170, node.RightChild);

        printBst(bst);

        Assertions.assertTrue(bst.IsBalanced(bst.Root));


    }

    public BSTNode getNodeByKey(BSTNode node, int nodeKey) {
        if (node == null) {
            return null;
        }

        if (node.NodeKey == nodeKey) {
            return node;
        }

        BSTNode searchingNode = getNodeByKey(node.LeftChild, nodeKey);
        if (searchingNode == null || searchingNode.NodeKey != nodeKey && node.RightChild != null) {
            searchingNode = getNodeByKey(node.RightChild, nodeKey);
        }

        return searchingNode;
    }

    public void printBst(BalancedBST bst) {
        ArrayList<ArrayList<BSTNode>> printArr = new ArrayList<>();
        _printBst(bst.Root, printArr);

        for (int i = 0; i < printArr.size(); i++) {
            for (int j = 0; j < printArr.get(i).size(); j++) {
                BSTNode node = printArr.get(i).get(j);
                if (node.Parent == null) {
                    System.out.print(printArr.get(i).get(j).NodeKey + " ");
                } else {
                    String prefix = node.NodeKey < node.Parent.NodeKey ? "L: " : "R: ";
                    System.out.print("(P " + node.Parent.NodeKey + " - " + prefix + node.NodeKey + ") ");
                }
            }
            System.out.println();
            System.out.println("---------------------------");
        }
    }

    private void _printBst(BSTNode node, ArrayList<ArrayList<BSTNode>> printArr) {
        if (node == null) {
            return;
        }

        if (printArr.size() == node.Level) {
            printArr.add(node.Level, new ArrayList<>());
        }

        printArr.get(node.Level).add(node);

        _printBst(node.LeftChild, printArr);
        _printBst(node.RightChild, printArr);
    }
}