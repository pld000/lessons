package array_bst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class aBSTTest {
    public aBST arrayBst;
    public aBST arrayBst2;

    @BeforeEach
    void setUp() {
        arrayBst = new aBST(3);
        arrayBst2 = new aBST(3);
    }

    @Test
    void findKeyIndex() {
        Assertions.assertEquals(0, arrayBst.FindKeyIndex(999), "Failed FindKeyIndex empty tree");

        _fillTree();

        Assertions.assertNull(arrayBst.FindKeyIndex(999), "Failed FindKeyIndex not found");
        Assertions.assertEquals(0, arrayBst.FindKeyIndex(80), "Failed root searching");

        int[] test = new int[]{80, 40, 120, 20, 60, 100, 140, 10, 30, 50, 70, 90, 110, 130, 150};

        for (int i = 0; i < test.length; i++) {
            Assertions.assertEquals(i, arrayBst.FindKeyIndex(test[i]), "Failed search index" + i);
        }

        _fillTree2();

        Assertions.assertEquals(-8, arrayBst2.FindKeyIndex(25), "Failed search slot for 25");
    }

    @Test
    void addKey() {
        Assertions.assertEquals(0, arrayBst.AddKey(30), "Failed add key to empty tree");

        _fillTree();
        Assertions.assertEquals(-1, arrayBst.AddKey(999), "Failed add key for filled tree, haven't free slot");

        int[] test = new int[]{80, 40, 120, 20, 60, 100, 140, 10, 30, 50, 70, 90, 110, 130, 150};
        for (int i = 0; i < test.length; i++) {
            Assertions.assertEquals(i, arrayBst.AddKey(test[i]), "Failed add key for filled tree " + i);
        }

        _fillTree2();
        for (int i = 0; i < test.length; i++) {
            Assertions.assertEquals(i, arrayBst2.AddKey(test[i]), "Failed add key for tree with empty slots" + i);
        }
    }

    private void _fillTree() {
        arrayBst.Tree[0] = 80;
        arrayBst.Tree[1] = 40;
        arrayBst.Tree[2] = 120;
        arrayBst.Tree[3] = 20;
        arrayBst.Tree[4] = 60;
        arrayBst.Tree[5] = 100;
        arrayBst.Tree[6] = 140;
        arrayBst.Tree[7] = 10;
        arrayBst.Tree[8] = 30;
        arrayBst.Tree[9] = 50;
        arrayBst.Tree[10] = 70;
        arrayBst.Tree[11] = 90;
        arrayBst.Tree[12] = 110;
        arrayBst.Tree[13] = 130;
        arrayBst.Tree[14] = 150;
    }

    private void _fillTree2() {
        arrayBst2.Tree[0] = 80;
        arrayBst2.Tree[1] = 40;
        arrayBst2.Tree[2] = null; //120;
        arrayBst2.Tree[3] = 20;
        arrayBst2.Tree[4] = 60;
        arrayBst2.Tree[5] = null; //100;
        arrayBst2.Tree[6] = 140;
        arrayBst2.Tree[7] = null; //10;
        arrayBst2.Tree[8] = null; //30;
        arrayBst2.Tree[9] = null; //50;
        arrayBst2.Tree[10] = 70;
        arrayBst2.Tree[11] = 90;
        arrayBst2.Tree[12] = 110;
        arrayBst2.Tree[13] = null; //130;
        arrayBst2.Tree[14] = null; //150;
    }
}