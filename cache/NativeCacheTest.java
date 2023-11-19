package cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    }

    @Test
    void putToFilled() {
        String someKey = "someKey";
        String someValue = "someValue";
        String anotherSomeValue = "anotherSomeValue";

        String testSomeKey = testKeys[4] + "_" + "someKey";
        String testSomeValue = testKeys[4] + "_" + "someValue";

        for (int i = 0; i < testKeys.length; i++) {
            cache.put(testKeys[i] + "_" + someKey, testKeys[i] + "_" + someValue);
        }

        Assertions.assertEquals(testSomeValue,  cache.get(testSomeKey), "Failed putToFilled get testSomeValue");

        cache.put(testSomeKey, anotherSomeValue);
        Assertions.assertEquals(anotherSomeValue,  cache.get(testSomeKey), "Failed update existing key");
    }

    @Test
    void get() {
        Assertions.assertNull(cache.get("some_key"), "Failed get from empty cache");

        String someKey = "someKey";
        String someValue = "someValue";

        for (int i = 0; i < testKeys.length; i++) {
            cache.put(testKeys[i], testKeys[i] + "_" + someValue);
        }

        for (int i = 0; i < testKeys.length; i++) {
            String value = testKeys[i] + "_" + someValue;
            Assertions.assertEquals(value, cache.get(testKeys[i]), "Failed get from filled cache " + i);
        }

        for (int i = 0; i < cache.hits.length; i++) {
            Assertions.assertEquals(1, cache.hits[i], "Failed hits count " + i);
        }
    }

    @Test
    void getManyTimes() {
        String someKey = "someKey";
        String someValue = "someValue";

        cache.put(someKey, someValue);

        cache.get(someKey);
        cache.get(someKey);
        cache.get(someKey);
        Assertions.assertEquals(someValue, cache.get(someKey), "Failed get a lot of calls");
        Assertions.assertEquals(4, cache.hits[cache.hashFun(someKey)], "Failed hits count after get a lot of calls");
    }

    @Test
    void getRightReplacementSlot() {
        String someKey = "someKey";
        String someValue = "someValue";

        int slotBeforeFilling = cache._seekSlot(someKey);

        for (int i = 0; i < testKeys.length; i++) {
            cache.put(testKeys[i], testKeys[i] + "_" + someValue);
        }

        String busyKeySlot = cache.slots[slotBeforeFilling];
        // Give busy slot more hints
        cache.get(busyKeySlot);
        cache.get(busyKeySlot);
        cache.get(busyKeySlot);

        int slotAfterFilling = cache._seekSlot(someKey);
        String busyKeySlotAfterFilling = cache.slots[slotAfterFilling];
        cache.get(busyKeySlotAfterFilling);
        cache.get(busyKeySlotAfterFilling);

        int suitableSlot = cache._seekSlot(someKey);
        cache.put(someKey, someValue);
        Assertions.assertEquals(someValue, cache.get(someKey), "Failed getRightReplacementSlot afters hints increase");
        Assertions.assertEquals(someValue, cache.values[suitableSlot], "Failed getRightReplacementSlot suitableSlot");
    }
}