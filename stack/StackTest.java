package stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    public Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        this.stack = new Stack<Integer>();
    }

    @AfterEach
    void tearDown() {
        this.stack = null;
    }

    @Test
    void size() {
        int emptySize = this.stack.size();
        int size = 10;

        Assertions.assertEquals(0, emptySize, "Empty size error");

        for (int i = 0; i < size; i++) {
            int value = (int) (Math.random() * 100);
            this.stack.push(value);
        }

        Assertions.assertEquals(size, this.stack.size(), "Wrong size after push");

        this.stack.pop();

        Assertions.assertEquals(--size, this.stack.size(), "Wrong size after pop");

    }

    @Test
    void pop() {
        int size = 5;
        int someValue = 999;

        Assertions.assertNull(this.stack.pop(), "Failed pop empty null");
        Assertions.assertEquals(0, this.stack.size(), "Failed size for empty stack");

        this.stack.push(someValue);

        Assertions.assertEquals(someValue, this.stack.pop(), "Failed pop wrong value");

        for (int i = 0; i < size; i++) {
            this.stack.push(i + 1);
        }

        Assertions.assertEquals(5, this.stack.pop(), "Failed pop for not empty stack");
        size -= 1;
        Assertions.assertEquals(size, this.stack.size(), "Failed pop size for not empty stack");

        while (this.stack.size() > 0) {
            this.stack.pop();
        }

        Assertions.assertEquals(0, this.stack.size(), "Failed pop all stack");
        Assertions.assertNull(this.stack.pop(), "Failed pop empty stack all");
    }

    @Test
    void push() {
        int size = 10;

        this.stack.push(999);
        Assertions.assertEquals(1, this.stack.size(), "Failed push size for one value");

        for (int i = 0; i < size; i++) {
            this.stack.push(i + 1);
        }
        Assertions.assertEquals(++size, this.stack.size(), "Failed push size");
    }

    @Test
    void peek() {
        int someValue = 999;
        Assertions.assertNull(this.stack.peek(), "Failed empty stack peek");

        this.stack.push(someValue);

        Assertions.assertEquals(someValue, this.stack.peek(), "Failed push size");
        Assertions.assertEquals(1, this.stack.size(), "Failed push size");
    }
}