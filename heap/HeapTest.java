package heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void add() {
    }
}