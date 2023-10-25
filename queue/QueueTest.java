package queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    public Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        this.queue = new Queue<Integer>();
    }

    @AfterEach
    void tearDown() {
        this.queue = null;
    }

    @Test
    void enqueue() {
    }

    @Test
    void dequeue() {
    }

    @Test
    void size() {
        Assertions.assertEquals(0, this.queue.size(), "Failed size empty size");

        int enqueueSize = 4;
        for (int i = 0; i < enqueueSize; i++) {
            this.queue.enqueue(i + 1);
        }
        Assertions.assertEquals(enqueueSize, this.queue.size(), "Failed size after enqueue");

//        int dequeueSize = (int) (Math.random() * 100);
//        for (int i = 0; i < enqueueSize; i++) {
//            var value = this.queue.dequeue();
//            System.out.println("Queue value: " + value);
//        }
//        int _sizeAfterDequeue = enqueueSize - dequeueSize;
//        Assertions.assertEquals(_sizeAfterDequeue, this.queue.size(), "Failed size after dequeue");
    }
}