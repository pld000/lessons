package queue;

import java.util.*;

public class Queue<T> {
    private QueueNode<T> _queueEnd;
    private QueueNode<T> _queueBegin;

    public Queue() {
        _queueEnd = null;
        _queueBegin = null;
    }

    public void enqueue(T item) {
        QueueNode<T> node = new QueueNode<T>(item);

        if (_queueEnd == null) {
            _queueEnd = node;
        } else {
            _queueBegin.next = node;
            node.prev = _queueBegin;
        }
        _queueBegin = node;
    }

    public T dequeue() {
        if (_queueBegin == null) {
            return null;
        }

        T _value = _queueBegin.value;
        _queueBegin = _queueBegin.prev;

        if (_queueBegin != null) {
            _queueBegin.next = null;
        }

        return _value;
    }

    public int size() {
        int _size = 0;
        QueueNode<T> node = _queueEnd;

        while (node != null) {
            _size++;
            node = node.next;
        }

        return _size;
    }
}


class QueueNode<T> {
    public T value;
    public QueueNode<T> next;
    public QueueNode<T> prev;

    public QueueNode(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}
