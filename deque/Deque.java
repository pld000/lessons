package deque;

public class Deque<T> {
    private Node<T> _head;
    private Node<T> _tail;

    public Deque() {
        _head = null;
        _tail = null;
    }

    public void addFront(T item) {
        Node<T> node = new Node<T>(item);
        if (_head == null) {
            _head = node;
            _tail = node;
        } else {
            _head.prev = node;
            node.next = _head;
            _head = node;
        }
    }

    public void addTail(T item) {
        Node<T> node = new Node<T>(item);
        if (_head == null) {
            _head = node;
        } else {
            _tail.next = node;
            node.prev = _tail;
        }

        _tail = node;
    }

    public T removeFront() {
        if (_head == null) {
            return null;
        }

        T value = _head.value;

        if (_head.next == null) {
            _head = null;
            _tail = null;
        } else {
            _head = _head.next;
            _head.prev = null;
        }

        return value;
    }

    public T removeTail() {
        if (_tail == null) {
            return null;
        }

        T value = _tail.value;

        if (_tail.prev == null) {
            _tail = null;
            _head = null;
        } else {
            _tail = _tail.prev;
            _tail.next = null;
        }

        return value;
    }

    public int size() {
        int _size = 0;
        Node<T> node = _head;

        while (node != null) {
            _size++;
            node = node.next;
        }

        return _size;
    }
}

class Node<T> {
    public Node<T> next;
    public Node<T> prev;
    public T value;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}