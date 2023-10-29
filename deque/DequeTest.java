package deque;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
    public Deque<String> deque;

    @BeforeEach
    void setUp() {
        deque = new Deque<String>();
    }

    @AfterEach
    void tearDown() {
        deque = null;
    }

    @Test
    void addFront() {
        String someString = "some string";
        deque.addFront(someString);
        Assertions.assertEquals(1, deque.size(), "addFront one element size");

        this.deque.removeFront();

        int _size = 100;
        for (int i = 0; i < _size; i++) {
            deque.addFront("value_" + (i + 1));
        }
        Assertions.assertEquals(_size, deque.size(), "addFront mane elements size");
    }

    @Test
    void addTail() {
        String someString = "some string";
        deque.addTail(someString);
        Assertions.assertEquals(1, deque.size(), "addFront one element size");

        this.deque.removeTail();

        int _size = 100;
        for (int i = 0; i < _size; i++) {
            deque.addTail("value_" + (i + 1));
        }
        Assertions.assertEquals(_size, deque.size(), "addFront mane elements size");
    }

    @Test
    void removeFront() {
        String value = deque.removeFront();
        Assertions.assertNull(value, "removeFront empty deque");
        Assertions.assertEquals(0, deque.size(), "removeFront empty deque");

        int _size = 200;
        for (int i = 0; i < _size; i++) {
            deque.addFront("value_" + (i + 1));
        }

        int sizeReducing = (int) (Math.random() * 100);
        for (int i = 0; i < sizeReducing; i++) {
            String val = deque.removeFront();
            System.out.println(val + " " + sizeReducing);
        }
        Assertions.assertEquals(_size - sizeReducing, deque.size(), "removeFront get size after removeFront");
    }

    @Test
    void removeTail() {
        String value = deque.removeFront();
        Assertions.assertNull(value, "removeFront empty deque");
        Assertions.assertEquals(0, deque.size(), "removeFront empty deque");

        int _size = 200;
        for (int i = 0; i < _size; i++) {
            deque.addTail("value_" + (i + 1));
        }

        int sizeReducing = (int) (Math.random() * 100);
        for (int i = 0; i < sizeReducing; i++) {
            String val = deque.removeTail();
            System.out.println(val + " " + sizeReducing);
        }
        Assertions.assertEquals(_size - sizeReducing, deque.size(), "removeTail get size after removeTail");
    }

    @Test
    void size() {
        Assertions.assertEquals(0, deque.size(), "get size for empty deque");

        int _size = 100;
        for (int i = 0; i < _size; i++) {
            deque.addTail("value_" + (i + 1));
        }
        Assertions.assertEquals(_size, deque.size(), "get size after addTail");

        for (int i = 0; i < _size; i++) {
            deque.addFront("value_" + (i + 1));
        }
        _size += _size;
        Assertions.assertEquals(_size, deque.size(), "get size after addFront");

        int sizeReducing = (int) (Math.random() * 100);
        for (int i = 0; i < sizeReducing; i++) {
            String val = deque.removeFront();
            System.out.println(val + " " + sizeReducing);
        }
        Assertions.assertEquals(_size - sizeReducing, deque.size(), "get size after removeFront");

        for (int i = 0; i < sizeReducing; i++) {
            deque.removeTail();
        }
        Assertions.assertEquals(_size - sizeReducing - sizeReducing, deque.size(), "get size after removeTail");
    }
}