package queue;

import java.util.*;

public class Queue<T> {
    private QueueNode<T> _queueEnd;
    private QueueNode<T> _queueBegin;

    public Queue() {
        this._queueEnd = null;
        this._queueBegin = null;
    }

    public void enqueue(T item) {
        QueueNode<T> node = new QueueNode<T>(item);

        if (this._queueBegin == null) {
            this._queueBegin = node;
        } else {
            node.prev = this._queueEnd;
        }
        this._queueEnd = node;
        System.out.println(this._queueBegin.prev + " - " + this._queueEnd.prev);
    }

    public T dequeue() {
        if (this._queueBegin == null) {
            return null;
        }

        T _value = this._queueBegin.value;
        this._queueBegin = this._queueBegin.prev;

        return _value;
    }

    public int size() {
        int _size = 0;
        QueueNode<T> node = this._queueBegin;

        while (node != null) {
            _size++;
            node = node.prev;
        }

        return _size;
    }
}


class QueueNode<T> {
    public T value;
    public QueueNode<T> prev;

    public QueueNode(T _value) {
        value = _value;
        prev = null;
    }
}
