package queue;

public class QueueUtils<T> {
    public void rotateQueue(Queue<T> queue, int rotationNumber) {
        if (queue.size() == 0) {
            return;
        }

        for (int i = 0; i < rotationNumber; i++) {
            T val = queue.dequeue();
            queue.enqueue(val);
        }
    }
}
