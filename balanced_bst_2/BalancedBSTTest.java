package balanced_bst_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedBSTTest {
    public BalancedBST bst;

    @BeforeEach
    void setUp() {
        bst = new BalancedBST();
    }

    @Test
    void generateTree() {
        // int[] test2 = {140, 20, 30, 150, 50, 60, 70, 90, 80, 100, 110, 120, 130, 500};
        int[] test2 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
        bst.GenerateTree(test2);

        printBst(bst);
    }

    @Test
    void isBalanced() {
    }

    public void printBst(BalancedBST bst) {
        _printBst(bst.Root);
    }

    private void _printBst(BSTNode node) {
        if (node == null) {
            //  System.out.print("null ");
            return;
        }

        if (node.Parent == null) {
            System.out.println(node.NodeKey);
        }

        if (node.LeftChild != null) {
            System.out.print(node.LeftChild.NodeKey + " ");
        }

        if (node.RightChild != null) {
            System.out.print(node.RightChild.NodeKey + " ");
        }

//        System.out.println(node.NodeKey + " --- " + node.Level);
//        System.out.println("-----");
        System.out.println();
        System.out.println("----------------------------------");
        _printBst(node.LeftChild);
        _printBst(node.RightChild);
    }
}