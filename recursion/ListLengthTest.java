package recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListLengthTest {

    @Test
    void getLength() {
        ListLength<Integer> listLength = new ListLength<>();

        List<Integer> emptyList = new List<>(new ArrayList<>());
        Assertions.assertEquals(0, listLength.getLength(emptyList), "Failed empty list");

        ArrayList<Integer> list = new ArrayList<>();
        int length = 187;

        for (int i = 0; i < length; i++) {
            list.add(i);
        }

        Assertions.assertEquals(length, listLength.getLength(new List<>(list)), "Failed list");
    }
}