package queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueUtilsTest {

    @Test
    void rotateEmpty() {
        int rotationNumber = 123;
        Queue<Integer> queue = new Queue<>();
        QueueUtils<Integer> queueUtils = new QueueUtils<>();
        queueUtils.rotateQueue(queue, rotationNumber);
        Assertions.assertEquals(0, queue.size());

    }

    @Test
    void rotateOneElement() {
        int someValue = 999;
        int rotationNumber = 123;
        Queue<Integer> queue = new Queue<>();
        QueueUtils<Integer> queueUtils = new QueueUtils<>();

        queue.enqueue(someValue);

        queueUtils.rotateQueue(queue, rotationNumber);
        Assertions.assertEquals(1, queue.size(), "Failed rotateOneElement size");
        Assertions.assertEquals(someValue, queue.dequeue(), "Failed rotateOneElement dequeue");
    }

    @Test
    void rotateByOneElement() {
        int rotationNumber = 100;
        Queue<Integer> queue = new Queue<>();
        QueueUtils<Integer> queueUtils = new QueueUtils<>();

        int size = 16;
        for (int i = 0; i < size; i++) {
            queue.enqueue(i + 1);
        }

        queueUtils.rotateQueue(queue, rotationNumber);
        Assertions.assertEquals(size, queue.size(), "Failed rotateByOneElement size");
        Assertions.assertEquals(5, queue.dequeue(), "Failed rotateByOneElement size");

    }
}