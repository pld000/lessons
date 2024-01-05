package secondary_max;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecondaryMaxTest {
    @Test
    void getSecondMax() {
        SecondaryMax secondaryMax = new SecondaryMax();

        int[] numbers1 = {3, 4, 5, 2, 8};
        Assertions.assertEquals(5, secondaryMax.getSecondMax(numbers1), "Failed - numbers1");

        int[] numbers2 = {3, 4, 5, 2, 5};
        Assertions.assertEquals(5, secondaryMax.getSecondMax(numbers2), "Failed - numbers2");

        int[] numbers3 = {0, 1, 0, 1, 0};
        Assertions.assertEquals(1, secondaryMax.getSecondMax(numbers3), "Failed - numbers3");

        int[] numbers4 = {7, 7, 7, 7, 5, 5, 5, 2, 2, 2, 3, 3, 3, 6, 6, 6, 7, 7, 7};
        Assertions.assertEquals(7, secondaryMax.getSecondMax(numbers4), "Failed - numbers4");

        int[] numbers5 = {0, 0, 0, 0, 0};
        Assertions.assertEquals(0, secondaryMax.getSecondMax(numbers5), "Failed - numbers5");

    }
}