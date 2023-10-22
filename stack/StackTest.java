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
        System.out.println("---------BeforeEach----------");
    }

    @AfterEach
    void tearDown() {
        this.stack = null;
        System.out.println("---------AfterEach----------");
    }

    @Test
    void test() {
        int size = 10;

        for (int i = 0; i <= size; i++) {
            int value = (int) (Math.random() * 100);
            this.stack.push(value);
        }

        Assertions.assertEquals(size, this.stack.size(), "Wrong size");
        Assertions.assertEquals(size, this.stack.size(), "Wrong size");
    }
}