package queue;

import java.util.*;

public class Queue<T> {
    private QueueNode<T> _head;
    private QueueNode<T> _tail;

    public Queue() {
        _head = null;
        _tail = null;
    }

    public void enqueue(T item) {
        QueueNode<T> node = new QueueNode<T>(item);

        if (_head == null) {
            _head = node;
        } else {
            _tail.next = node;
            node.prev = _tail;
        }
        _tail = node;
    }

    public T dequeue() {
        if (_head == null) {
            return null;
        }

        T _value = _head.value;
        _head = _head.next;

        if (_head == null) {
            _tail = null;
        } else {
            _head.prev = null;
        }

        return _value;
    }

    public int size() {
        int _size = 0;
        QueueNode<T> node = _head;

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
