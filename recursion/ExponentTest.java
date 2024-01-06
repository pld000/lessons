package recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExponentTest {

    @Test
    void pow() {
        Exponent exponent = new Exponent();

        Assertions.assertEquals(8, exponent.pow(2, 3), "Failed 2 to 3");
        Assertions.assertEquals(1, exponent.pow(32423, 0), "Failed 32423 to 0");
        Assertions.assertEquals(27, exponent.pow(3, 3), "Failed 3 to 3");
        Assertions.assertEquals(81, exponent.pow(9, 2), "Failed 9 to 2");
        Assertions.assertEquals(231, exponent.pow(231, 1), "Failed 231 to 1");
    }
}