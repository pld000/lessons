package recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PalindromeTest {

    public String[] testingStringsTrue = {"дед", //true
            "еле", // true
            "еле-еле", // true
            "манекенам", // true
            "матам", // true
    };
    public String[] testingStringsFalse = {"машина", // false
            "небо", // false
            "пар", // false
            "параурап", // false
    };

    @Test
    void check() {
        for (int i = 0; i < testingStringsTrue.length; i++) {
            Assertions.assertTrue(Palindrome.check(testingStringsTrue[i]), "Failed true testing " + i);
        }

        for (int i = 0; i < testingStringsFalse.length; i++) {
            Assertions.assertFalse(Palindrome.check(testingStringsFalse[i]), "Failed false testing " + i);
        }
    }
}