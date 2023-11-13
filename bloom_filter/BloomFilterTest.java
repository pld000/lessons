package bloom_filter;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloomFilterTest {
    public BloomFilter bloomFilter;
    public String[] testCases = {"0123456789", "1234567890", "2345678901", "3456789012", "4567890123", "5678901234", "6789012345", "7890123456", "8901234567", "9012345678",};

    @BeforeEach
    void setUp() {
        bloomFilter = new BloomFilter(32);
    }

    @AfterEach
    void tearDown() {
        bloomFilter = null;
    }

    @Test
    void test() {
        String someString = "some_string";
        Assertions.assertFalse(bloomFilter.isValue(someString), "is value empty");

        for (int i = 0; i < testCases.length; i++) {
            bloomFilter.add(testCases[i]);
        }

        for (int i = 0; i < testCases.length; i++) {
            Assertions.assertTrue(bloomFilter.isValue(testCases[i]), "is value true filled, line " + i);
        }

        for (int i = 0; i < testCases.length; i++) {
            Assertions.assertFalse(bloomFilter.isValue(testCases[i] + "_false"), "is value false filled, line " + i);
        }
    }

    @Test
    void test2() {
        Integer[] _testIndexes = {2, 5, 7, 9};
        String[] _testCases = {getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()};

        for (Integer testIndex : _testIndexes) {
            bloomFilter.add(_testCases[testIndex]);
        }

        for (int i = 0; i < _testCases.length; i++) {
            if (Arrays.asList(_testIndexes).contains(i)) {
                Assertions.assertTrue(bloomFilter.isValue(_testCases[i]), "separated is value true filled, line " + i);
            } else {
                Assertions.assertFalse(bloomFilter.isValue(_testCases[i]), "separated is value false filled, line " + i);
            }
        }
    }

    public String getRandomString() {
        int leftLimit = 48; // цифра '0'
        int rightLimit = 122; // буква 'z'
        int targetStringLength = 40;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}