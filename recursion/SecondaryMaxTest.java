package recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class SecondaryMaxTest {
    @Test
    void getSecondMax() {
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(3, 4, 5, 2, 8));
        Assertions.assertEquals(5, SecondaryMax.getSecondMax(numbers1), "Failed - numbers1");

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(3, 4, 5, 2, 5));
        Assertions.assertEquals(5, SecondaryMax.getSecondMax(numbers2), "Failed - numbers2");

        ArrayList<Integer> numbers3 = new ArrayList<>(Arrays.asList(0, 1, 0, 1, 0));
        Assertions.assertEquals(1, SecondaryMax.getSecondMax(numbers3), "Failed - numbers3");

        ArrayList<Integer> numbers4 = new ArrayList<>(Arrays.asList(7, 7, 7, 7, 5, 5, 5, 2, 2, 2, 3, 3, 3, 6, 6, 6, 7, 7, 7));
        Assertions.assertEquals(7, SecondaryMax.getSecondMax(numbers4), "Failed - numbers4");

        ArrayList<Integer> numbers5 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        Assertions.assertEquals(0, SecondaryMax.getSecondMax(numbers5), "Failed - numbers5");

        ArrayList<Integer> numbers6 = new ArrayList<>(Arrays.asList(8));
        Assertions.assertEquals(8, SecondaryMax.getSecondMax(numbers6), "Failed - numbers6");

        ArrayList<Integer> numbers7 = new ArrayList<>(Arrays.asList(7, 8));
        Assertions.assertEquals(7, SecondaryMax.getSecondMax(numbers7), "Failed - numbers7");

        ArrayList<Integer> numbers8 = new ArrayList<>();
        Assertions.assertNull(SecondaryMax.getSecondMax(numbers8), "Failed - numbers8");

    }
}