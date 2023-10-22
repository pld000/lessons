package stack;

import java.util.*;

public class Stack<T> {
    private Node<T> head;
    private Node<T> tail;

    public Stack() {
        this.head = null;
        this.tail = null;
    }

    public int size() {
        int res = 0;
        Node<T> node = this.head;

        while (node != null) {
            res++;
            node = node.next;
        }

        return res;
    }

    public T pop() {
        if (this.tail != null) {
            T value = this.tail.value;
            this.tail = this.tail.prev;

            return value;
        }
        return null;
    }

    public void push(T val) {
        Node<T> _item = new Node<T>(val);
        if (this.head == null) {
            this.head = _item;
        } else {
            _item.prev = this.tail;
            this.tail.next = _item;
        }

        this.tail = _item;
    }

    public T peek() {
        if (this.tail != null) {
            return this.tail.value;
        }
        return null;
    }
}

class Node<T> {
    public Node<T> next;
    public Node<T> prev;
    public T value;

    public Node(T _value) {
        this.next = null;
        this.prev = null;
        this.value = _value;
    }
}