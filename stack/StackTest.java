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

        Assertions.assertNull(this.stack.pop(), "Wrong empty null");

        for (int i = 0; i < size; i++) {
            int value = (int) (Math.random() * 100);
            this.stack.push(value);
        }

        this.stack.push(someValue);

        Assertions.assertEquals(someValue, this.stack.pop(), "Wrong value pop");

        while (this.stack.size() > 0) {
            this.stack.pop();
        }

        Assertions.assertEquals(0, this.stack.size(), "Failed after poping) all stack");
        Assertions.assertNull(this.stack.pop(), "Failed empty stack after poping all");
    }

    @Test
    void push() {
        int size = 10;

        for (int i = 0; i < size; i++) {
            int value = (int) (Math.random() * 100);
            this.stack.push(value);
        }

        Assertions.assertEquals(size, this.stack.size(), "Failed push size");
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