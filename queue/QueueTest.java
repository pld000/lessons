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
        int someValue = 999;
        this.queue.enqueue(someValue);
        Assertions.assertEquals(1, this.queue.size(), "Failed enqueue check size before dequeue");
        Assertions.assertEquals(someValue, this.queue.dequeue(), "Failed enqueue one element");
        Assertions.assertEquals(0, this.queue.size(), "Failed enqueue check size after dequeue");

        int enqueueSize = 300;
        for (int i = 0; i < enqueueSize; i++) {
            this.queue.enqueue(i + 1);
        }
        Assertions.assertEquals(enqueueSize, this.queue.size(), "Failed enqueue 300 elements");
    }

    @Test
    void dequeue() {
        var value = this.queue.dequeue();
        Assertions.assertNull(value, "Failed dequeue empty list");

        int someValue = 999;
        this.queue.enqueue(someValue);
        var enqueuedValue = this.queue.dequeue();
        Assertions.assertEquals(someValue, enqueuedValue, "Failed dequeue one value");

        int enqueueSize = 100;
        int dequeueSize = (int) (Math.random() * 100);

        for (int i = 0; i < enqueueSize; i++) {
            this.queue.enqueue(i + 1);
        }

        for (int i = 0; i < dequeueSize; i++) {
            this.queue.dequeue();
        }

        int _sizeAfterDequeue = enqueueSize - dequeueSize;
        Assertions.assertEquals(_sizeAfterDequeue, this.queue.size(), "Failed dequeue after many elements dequeue");
    }

    @Test
    void size() {
        Assertions.assertEquals(0, this.queue.size(), "Failed size empty size");

        int enqueueSize = 100;
        for (int i = 0; i < enqueueSize; i++) {
            this.queue.enqueue(i + 1);
        }
        Assertions.assertEquals(enqueueSize, this.queue.size(), "Failed size after enqueue");

        int dequeueSize = (int) (Math.random() * 100);
        for (int i = 0; i < dequeueSize; i++) {
            var value = this.queue.dequeue();
            System.out.println("Queue value: " + value);
        }
        int _sizeAfterDequeue = enqueueSize - dequeueSize;
        Assertions.assertEquals(_sizeAfterDequeue, this.queue.size(), "Failed size after dequeue");
    }
}