package hashing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void hashFun() {
        String some = "asdfsdaf";
        for(int i=0;i< some.length(); i++) {
            System.out.println(+ some.charAt(i));
            System.out.println((int) some.charAt(i));
        }
    }

    @Test
    void seekSlot() {
    }

    @Test
    void put() {
    }

    @Test
    void find() {
    }
}