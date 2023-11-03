package hashing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable hashTable;

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


//        String[] testStrings = {
//                "ab", "ba", "some name", "name some", "equals", "equals"
//        };
//
//        for (int i = 0; i < testStrings.length; i++) {
//            System.out.println(hashTable.hashFun(testStrings[i]));
//        }

    }

    @Test
    void seekSlotForEmptyTable() {
        int emptyTableSeekSlot = hashTable.seekSlot("some string");
        Assertions.assertNotEquals(-1, emptyTableSeekSlot, "Failed seekSlot empty table");
    }

    @Test
    void seekSlotIfCollision() {
        int someNameSlot = hashTable.put("some name");
        int collisionSlot = hashTable.put("name sameeee");
        int collisionSlot3 = hashTable.put("name sameeee");
        int collisionSlot2 = hashTable.put("11aname some");

        System.out.println(someNameSlot + " " + collisionSlot+ " " + collisionSlot2+ " " + collisionSlot3);
//        Assertions.assertNotEquals(-1, emptyTableSeekSlot, "Failed seekSlot empty table");
    }

    @Test
    void put() {
    }

    @Test
    void find() {
    }
}