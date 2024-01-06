package recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumbersDigitSumTest {

    @Test
    void getSum() {
        Assertions.assertEquals(15, NumbersDigitSum.getSum(12345), "Failed 15");
        Assertions.assertEquals(36, NumbersDigitSum.getSum(9999), "Failed 9999");
        Assertions.assertEquals(0, NumbersDigitSum.getSum(0), "Failed 0");
        Assertions.assertEquals(1, NumbersDigitSum.getSum(1), "Failed 1");
        Assertions.assertEquals(22, NumbersDigitSum.getSum(8374), "Failed 8374");
    }
}