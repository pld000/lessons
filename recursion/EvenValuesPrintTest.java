package recursion;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EvenValuesPrintTest {

    @Test
    void print() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            list.add(i);
        }

        EvenValuesPrint.print(list);
    }
}