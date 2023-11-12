package bloom_filter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloomFilterTest {
    public BloomFilter bloomFilter;

    @BeforeEach
    void setUp() {
        bloomFilter = new BloomFilter(32);
    }

    @AfterEach
    void tearDown() {
        bloomFilter = null;
    }

    @Test
    void hash1() {
    }

    @Test
    void hash2() {
    }

    @Test
    void add() {
        String someString = "some_string";
        System.out.println(bloomFilter.hash1(someString));
        System.out.println(bloomFilter.hash2(someString));

        bloomFilter.add(someString);
        System.out.println(bloomFilter.filter);
    }

    @Test
    void isValue() {
    }
}