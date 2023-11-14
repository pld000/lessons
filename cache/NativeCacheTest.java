package cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NativeCacheTest {
    public NativeCache<String> cache;
    public String[] testKeys = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q"};

    @BeforeEach
    void setUp() {
        cache = new NativeCache<String>(17, String.class);
    }

    @AfterEach
    void tearDown() {
        cache = null;
    }

    @Test
    void put() {
        String someKey = "someKey";
        String someValue = "someValue";
        cache.put(someKey, someValue);
        int someKeyHash = cache.hashFun(someKey);
        Assertions.assertEquals(someValue, cache.values[someKeyHash], "put one element");

        for (int i=0; i< testKeys.length; i++) {
            cache.put(testKeys[i], testKeys[i] + "_" + someValue);
        }
        Assertions.assertNotEquals(someValue, cache.values[someKeyHash], "put with replacement");
    }

    @Test
    void get() {
    }
}