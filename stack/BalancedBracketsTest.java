package stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BalancedBracketsTest {
    public String[] testStrings = {"(((()())))", //true
            "))((", //false
            "(()()(()))", //true
            "()()(()))", //false
            "()(())(())", //true
            "(((()", //false
            "()))" //false
    };

    @Test
    void checkBalance() {
        Assertions.assertTrue(BalancedBrackets.checkBalance(testStrings[0]), "Failed string: " + 1);
        Assertions.assertFalse(BalancedBrackets.checkBalance(testStrings[1]), "Failed string: " + 2);
        Assertions.assertTrue(BalancedBrackets.checkBalance(testStrings[2]), "Failed string: " + 3);
        Assertions.assertFalse(BalancedBrackets.checkBalance(testStrings[3]), "Failed string: " + 4);
        Assertions.assertTrue(BalancedBrackets.checkBalance(testStrings[4]), "Failed string: " + 5);
        Assertions.assertFalse(BalancedBrackets.checkBalance(testStrings[5]), "Failed string: " + 6);
        Assertions.assertFalse(BalancedBrackets.checkBalance(testStrings[6]), "Failed string: " + 7);
    }

    @Test
    void checkBalanceCounter() {
        Assertions.assertTrue(BalancedBracketsCounter.checkBalance(testStrings[0]), "Failed string: " + 0);
        Assertions.assertFalse(BalancedBracketsCounter.checkBalance(testStrings[1]), "Failed string: " + 1);
        Assertions.assertTrue(BalancedBracketsCounter.checkBalance(testStrings[2]), "Failed string: " + 2);
        Assertions.assertFalse(BalancedBracketsCounter.checkBalance(testStrings[3]), "Failed string: " + 3);
        Assertions.assertTrue(BalancedBracketsCounter.checkBalance(testStrings[4]), "Failed string: " + 4);
        Assertions.assertFalse(BalancedBracketsCounter.checkBalance(testStrings[5]), "Failed string: " + 5);
    }

    @Test
    void inverseCheckBalance() {
        Assertions.assertTrue(InverseStackBalancedBrackets.checkBalance(testStrings[0]), "Failed string: " + 0);
        Assertions.assertFalse(InverseStackBalancedBrackets.checkBalance(testStrings[1]), "Failed string: " + 1);
        Assertions.assertTrue(InverseStackBalancedBrackets.checkBalance(testStrings[2]), "Failed string: " + 2);
        Assertions.assertFalse(InverseStackBalancedBrackets.checkBalance(testStrings[3]), "Failed string: " + 3);
        Assertions.assertTrue(InverseStackBalancedBrackets.checkBalance(testStrings[4]), "Failed string: " + 4);
        Assertions.assertFalse(InverseStackBalancedBrackets.checkBalance(testStrings[5]), "Failed string: " + 5);
    }
}