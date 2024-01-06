package recursion;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EvenIndexPrintTest {

    @Test
    void print() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(10000 + i);
        }

        EvenIndexPrint.print(list);
    }
}