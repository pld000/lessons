package balanced_bst_array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsDataStructures2Test {

    @BeforeEach
    void setUp() {
    }

    @Test
    void generateBBSTArray() {
        int[] some = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] bst = AlgorithmsDataStructures2.GenerateBBSTArray(some);

        for (int i = 0; i < bst.length; i++) {
            System.out.print(bst[i] + ", ");
        }
    }
}