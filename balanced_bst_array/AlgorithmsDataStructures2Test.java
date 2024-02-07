package balanced_bst_array;

import binary_search_tree.BST;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsDataStructures2Test {
    public BST<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BST<>(null);
        int[] test2 = {140, 20, 30, 150, 50, 60, 70, 90, 80, 100, 110, 120, 130, 500};
        int[] balancedTree = AlgorithmsDataStructures2.GenerateBBSTArray(test2);

        for (int i = 0; i < balancedTree.length; i++) {
            bst.AddKeyValue(balancedTree[i], balancedTree[i]);
        }
    }

    @Test
    void generateBBSTArray() {
        int[] test1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] test2 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
        int[] bst = AlgorithmsDataStructures2.GenerateBBSTArray(test1);

        for (int i = 0; i < bst.length; i++) {
            System.out.print(bst[i] + ", ");
        }
    }

    @Test
    void finMinMax() {
        Assertions.assertEquals(20, bst.FinMinMax(bst.Root, false).NodeKey, "Failed root finMinMax min");
        Assertions.assertEquals(500, bst.FinMinMax(bst.Root, true).NodeKey, "Failed root finMinMax max");
    }
}