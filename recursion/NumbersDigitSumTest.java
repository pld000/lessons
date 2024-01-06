package recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumbersDigitSumTest {

    @Test
    void getSum() {
        NumbersDigitSum digitSum = new NumbersDigitSum();

        Assertions.assertEquals(15, digitSum.getSum(12345), "Failed 15");
        Assertions.assertEquals(36, digitSum.getSum(9999), "Failed 9999");
        Assertions.assertEquals(0, digitSum.getSum(0), "Failed 0");
        Assertions.assertEquals(1, digitSum.getSum(1), "Failed 1");
        Assertions.assertEquals(22, digitSum.getSum(8374), "Failed 8374");
    }
}