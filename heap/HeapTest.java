package heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void makeHeap() {
        int[] test = {2, 3, 4, 8, 9, 13, 41, 73, 1, 6, 100, 150, 25, 67, 29};

        Heap heap = new Heap();
        heap.MakeHeap(test, 3);

        for (int i = 0; i < heap.HeapArray.length; i++) {
            System.out.print(heap.HeapArray[i] + ", ");
        }
    }

    @Test
    void getMax() {
        Heap heap = new Heap();
        Assertions.assertEquals(-1, heap.GetMax(), "Failed GetMax empty");

        int[] test = {2, 3, 4, 8, 9, 13, 41, 73, 1, 6, 100, 150, 25, 67, 29};
        heap.MakeHeap(test, 3);

        Arrays.sort(test);

        for (int i = test.length - 1; i >= 0; i--) {
            Assertions.assertEquals(test[i], heap.GetMax(), "Failed GetMax for " + i);
        }

        Assertions.assertEquals(-1, heap.GetMax(), "Failed after get all maximums");
    }

    @Test
    void add() {
        Heap heapEmpty = new Heap();
        Assertions.assertFalse(heapEmpty.Add(999), "Failed adding to empty");

        int[] test = {2, 3, 4, 8, 9, 13, 41, 73, 1, 6, 100, 150, 25, 67, 29};
        int[] range1 = Arrays.copyOfRange(test, 0, 8);
        int[] range2 = Arrays.copyOfRange(test, 8, test.length);

        Heap heap = new Heap();
        heap.MakeHeap(range1, 3);

        Assertions.assertTrue(heap.Add(500), "Failed adding value 500");
        Assertions.assertEquals(500, heap.GetMax(), "Failed getMax from add");

        for (int i = 0; i < range2.length; i++) {
            Assertions.assertTrue(heap.Add(range2[i]), "Failed add range2 " + i);
        }

        Assertions.assertFalse(heap.Add(999), "Failed add after heap is filled");

        Arrays.sort(test);

        for (int i = test.length - 1; i >= 0; i--) {
            Assertions.assertEquals(test[i], heap.GetMax(), "Failed GetMax from add for " + i);
        }

        for (int i = 0; i < heap.HeapArray.length; i++) {
            System.out.print(heap.HeapArray[i] + ", ");
        }
    }
}