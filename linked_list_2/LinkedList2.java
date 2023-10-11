package linked_list_2;

import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value) {
        Node node = this.head;

        while (node != null) {
            if (node.value == _value) {
                return node;
            }

            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;

        while (node != null) {
            if (node.value == _value) {
                nodes.add(node);
            }

            node = node.next;
        }

        return nodes;
    }

    public boolean remove(int _value) {
        Node node = this.head;

        while (node != null) {
            if (node.value == _value) {
                if (node.prev == null) {
                    this.head = node.next;
                    this.head.prev = null;
                } else if (node.next == null) {
                    node.prev.next = null;
                    this.tail = node.prev;
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }

                return true;
            }

            node = node.next;
        }

        return false;
    }

    public void removeAll(int _value) {
        boolean flag = true;

        while (flag) {
            flag = this.remove(_value);
        }
    }

    public void clear() {
        while (this.head != null) {
            this.head = this.head.next;
        }

        this.tail = null;
    }

    public int count() {
        int result = 0;
        Node node = this.head;
        while (node != null) {
            result++;
            node = node.next;
        }

        return result;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null) {
            this.head.prev = _nodeToInsert;
            _nodeToInsert.next = this.head;
            _nodeToInsert.prev = null;

            this.head = _nodeToInsert;
        } else {
            if (_nodeAfter.next != null) {
                _nodeAfter.next.prev = _nodeToInsert;
            }

            _nodeToInsert.next = _nodeAfter.next;
            _nodeToInsert.prev = _nodeAfter;
            _nodeAfter.next = _nodeToInsert;
        }

        if (_nodeToInsert.next == null) {
            this.tail = _nodeToInsert;
        }
    }
}

class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }
}