package stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedBracketsTest {

    @Test
    void checkBalance() {
        String[] testStrings = {"(((()())))", //true
                "))((", //false
                "(()()(()))", //true
                "()()(()))", //false
                "()(())(())", //true
                "((())" //false
        };

        Assertions.assertTrue(BalancedBrackets.checkBalance(testStrings[0]), "Failed string: " + 0);
        Assertions.assertFalse(BalancedBrackets.checkBalance(testStrings[1]), "Failed string: " + 1);
        Assertions.assertTrue(BalancedBrackets.checkBalance(testStrings[2]), "Failed string: " + 2);
        Assertions.assertFalse(BalancedBrackets.checkBalance(testStrings[3]), "Failed string: " + 3);
        Assertions.assertTrue(BalancedBrackets.checkBalance(testStrings[4]), "Failed string: " + 4);
        Assertions.assertFalse(BalancedBrackets.checkBalance(testStrings[5]), "Failed string: " + 5);

    }
}