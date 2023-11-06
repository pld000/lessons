package dictionary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Native;

import static org.junit.jupiter.api.Assertions.*;

class NativeDictionaryTest {
    public NativeDictionary<String> dictionary;
    public String[] testKeys = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q"};

    @BeforeEach
    void setUp() {
        dictionary = new NativeDictionary<String>(17, String.class);
    }

    @AfterEach
    void tearDown() {
        dictionary = null;
    }

    @Test
    void isKeyForEmpty() {
        String someKey = "some_key";
        Assertions.assertFalse(dictionary.isKey(someKey), "Failed isKeyForEmpty");
    }

    @Test
    void isKeyForNotEmpty() {
        int aSlot = dictionary.hashFun("a");
        int bSlot = dictionary.hashFun("b");
        String aSlotString = "Test string for \"a\" key";

        dictionary.slots[bSlot] = "b";
        dictionary.slots[aSlot] = "a";
        dictionary.values[aSlot] = aSlotString;

        Assertions.assertTrue(dictionary.isKey("a"), "Failed isKeyForNotEmpty for a key");
        Assertions.assertTrue(dictionary.isKey("b"), "Failed isKeyForNotEmpty for b key");
        Assertions.assertFalse(dictionary.isKey("c"), "Failed isKeyForNotEmpty for c key that isn't exist");
    }

    @Test
    void putToEmpty() {
        String key1String = "Test value for key1";
        dictionary.put("key1", key1String);
        Assertions.assertTrue(dictionary.isKey("key1"), "Failed putToEmpty isKey");
        Assertions.assertEquals(key1String, dictionary.get("key1"), "Failed putToEmpty get");

        String key1String2 = "Test value for updated key1";
        dictionary.put("key1", key1String2);
        Assertions.assertNotEquals(key1String, dictionary.get("key1"), "Failed putToEmpty get changed value, not equals");
        Assertions.assertEquals(key1String2, dictionary.get("key1"), "Failed putToEmpty get changed value, equals");
    }

    @Test
    void put() {
        for (int i = 0; i < testKeys.length; i++) {
            dictionary.put(testKeys[i], "Some test value " + i);
        }

        for (int i = 0; i < testKeys.length; i++) {
            Assertions.assertTrue(dictionary.isKey(testKeys[i]), "Failed put isKey for full dictionary");
            Assertions.assertEquals("Some test value " + i, dictionary.get(testKeys[i]), "Failed put get for full dictionary");
        }
    }

    @Test
    void get() {
        Assertions.assertNull(dictionary.get("some_key"), "Failed get for empty");
    }
}