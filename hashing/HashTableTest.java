package hashing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable hashTable;
    public String[] testKeys = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q"};

    @BeforeEach
    void setUp() {
        hashTable = new HashTable(17, 3);
    }

    @AfterEach
    void tearDown() {
        hashTable = null;
    }

    @Test
    void hashFun() {
        String someValue = "This some test string that should be test hash function determination";
        Assertions.assertEquals(hashTable.hashFun(someValue), hashTable.hashFun(someValue), "Failed determination");

        String str1 = "asdfasdf";
        String str2 = "fdsafdsa";
        Assertions.assertEquals(hashTable.hashFun(str1), hashTable.hashFun(str2), "Failed different string has the same hash");
    }

    @Test
    void seekSlotForEmptyTable() {
        int emptyTableSeekSlot = hashTable.seekSlot("some string");
        Assertions.assertNotEquals(-1, emptyTableSeekSlot, "Failed seekSlot empty table");
    }

    @Test
    void seekSlotFullTable() {
        for (int i = 0; i < testKeys.length; i++) {
            hashTable.slots[hashTable.seekSlot(testKeys[i])] = testKeys[i];
        }

        for (int i = 0; i < testKeys.length; i++) {
            Assertions.assertEquals(testKeys[i], hashTable.slots[hashTable.seekSlot(testKeys[i])], "Failed seekSlotFullTable equals a");
        }

        Assertions.assertEquals(-1, hashTable.seekSlot("some value"), "Failed seekSlotFullTable not found ");
    }

    @Test
    void seekSlotOneEmptySlot() {
//        System.out.println(hashTable.hashFun("r")); //12
//        System.out.println(hashTable.hashFun("s")); //13
//        System.out.println(hashTable.hashFun("t"));
//        System.out.println(hashTable.hashFun("u"));
//        System.out.println(hashTable.hashFun("v"));
//        System.out.println(hashTable.hashFun("w"));
//        System.out.println(hashTable.hashFun("x"));
//        System.out.println(hashTable.hashFun("y"));
//        System.out.println(hashTable.hashFun("z"));

        for (int i = 0; i < testKeys.length; i++) {
            hashTable.slots[hashTable.seekSlot(testKeys[i])] = testKeys[i];
        }

        hashTable.slots[1] = null;
        Assertions.assertEquals(13, hashTable.hashFun("s"), "Failed seekSlotOneEmptySlot test case value");
        Assertions.assertEquals(1, hashTable.seekSlot("s"), "Failed seekSlotOneEmptySlot search one empty");
    }

    @Test
    void seekSlotWithCollision() {
        hashTable.slots[hashTable.hashFun("a")] = "a";
        int rSlot = hashTable.seekSlot("r");
        Assertions.assertEquals(15, rSlot, "Failed seekSlotWithCollision r slot");

        hashTable.slots[rSlot] = "r";
        int uSlot = hashTable.seekSlot("u");
        Assertions.assertEquals(1, uSlot, "Failed seekSlotWithCollision u slot");
    }

    @Test
    void putToEmpty() {
        String someString = "Any value";
        int strHash = hashTable.hashFun(someString);
        int strSlot = hashTable.put(someString);
        Assertions.assertEquals(strHash, strSlot, "Failed putToEmpty");
    }

    @Test
    void putToFull() {
        for (int i = 0; i < testKeys.length; i++) {
            hashTable.slots[hashTable.seekSlot(testKeys[i])] = testKeys[i];
        }

        String someString = "Any value";
        int strSlot = hashTable.put(someString);
        Assertions.assertEquals(-1, strSlot, "Failed putToFull");
    }

    @Test
    void putWithCollision() {
        hashTable.put("a");
        hashTable.put("r");
//        printTable();
        Assertions.assertEquals(12, hashTable.seekSlot("a"), "Failed putWithCollision a slot");
        Assertions.assertEquals(15, hashTable.seekSlot("r"), "Failed putWithCollision r slot");
    }


    @Test
    void findInEmptyTable() {
        String someString = "Any string";
        Assertions.assertEquals(-1, hashTable.find(someString), "Field findInEmptyTable");
    }

    @Test
    void findInTable() {
        int a = hashTable.put("a");
        int b = hashTable.put("b");
        int c = hashTable.put("c");
        int r = hashTable.put("r");

        Assertions.assertEquals(a, hashTable.find("a"), "Field findInTable a string");
        Assertions.assertEquals(b, hashTable.find("b"), "Field findInTable b string");
        Assertions.assertEquals(c, hashTable.find("c"), "Field findInTable c string");
        Assertions.assertEquals(r, hashTable.find("r"), "Field findInTable r string");
    }

    @Test
    void printTableTest() {
        System.out.println("---------------------hashTable------------------");
        for (int i = 0; i < testKeys.length; i++) {
            System.out.println(hashTable.hashFun(testKeys[i]));
        }
    }

    void printTable() {
        System.out.println("---------------------hashTable------------------");
        for (int i = 0; i < hashTable.slots.length; i++) {
            System.out.println(hashTable.slots[i]);
        }
    }

}