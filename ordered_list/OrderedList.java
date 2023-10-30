package ordered_list;

import java.util.*;


class Node<T> {
    public T value;
    public Node<T> next, prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T> {
    public Node<T> head, tail;
    private boolean _ascending;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(T v1, T v2) {
        return ((int) v1 < (int) v2) ? -1 : ((v1 == v2) ? 0 : 1);
    }

    public void add(T value) {
        int _count = count();
        Node<T> addedNode = new Node<>(value);

        if (_count == 0) {
            head = addedNode;
            tail = addedNode;
            return;
        }

        Node<T> node = head;

        for (int i = 0; i < _count; i++) {
            if (_ascending && compare(value, node.value) <= 0 || !_ascending && compare(value, node.value) >= 0) {
                if (node.prev == null) {
                    head.prev = addedNode;
                    addedNode.next = head;
                    head = addedNode;
                    return;
                }

                node.prev.next = addedNode;
                addedNode.prev = node.prev;
                addedNode.next = node;
                node.prev = addedNode;
                return;
            }

            node = node.next;
        }

        addedNode.prev = tail;
        tail.next = addedNode;
        tail = addedNode;
    }

    public Node<T> find(T val) {
        Node<T> node = head;

        for (int i = 0; i < count(); i++) {
            if (compare(val, node.value) == 0) {
                return node;
            } else if (_ascending && compare(val, node.value) < 0 || !_ascending && compare(val, node.value) > 0) {
                return null;
            }

            node = node.next;
        }

        return null;
    }

    public void delete(T val) {
        Node<T> node = find(val);

        if (node == null) {
            return;
        }

        if (node.prev == null && node.next == null) {
            head = null;
            tail = null;
        } else if (node.prev == null) {
            head = head.next;
            head.prev = null;
        } else if (node.next == null) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
    }

    public void clear(boolean asc) {
        _ascending = asc;
        head = null;
        tail = null;
    }

    public int count() {
        int _count = 0;

        Node<T> node = head;
        while (node != null) {
            _count++;
            node = node.next;
        }

        return _count;
    }

    ArrayList<Node<T>> getAll() {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}