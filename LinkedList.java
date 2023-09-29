import java.util.*;

public class LinkedList {
    public Node head;
    public Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void addInTail(Node item) {
        if (this.head == null) this.head = item;
        else this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value) return node;
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

        if (this.head != null && this.head.value == _value) {
            this.head = this.head.next;
            return true;
        }

        while (node != null) {
            if (node.next != null && node.next.value == _value) {
                node.next = node.next.next;

                if (node.next == null) {
                    this.tail = node;
                }

                return true;
            }
            node = node.next;
        }

        return false;
    }

    public void removeAll(int _value) {
        Node node = this.head;

        while (node != null) {
            if (this.head != null && this.head.value == _value) {
                this.head = this.head.next;
                node = node.next;
            } else if (node.next != null && node.next.value == _value) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }

            if (node != null && node.next == null) {
                this.tail = node;
            } else if (this.head == null) {
                this.tail = null;
            }
        }
    }

    public void clear() {
        while (this.head != null) {
            this.head = this.head.next;
        }

        this.tail = null;
    }

    public int count() {
        var result = 0;
        Node node = this.head;

        while (node != null) {
            result += 1;
            node = node.next;
        }

        return result;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null) {
            _nodeToInsert.next = this.head;
            this.head = _nodeToInsert;
        } else {
            _nodeToInsert.next = _nodeAfter.next;
            _nodeAfter.next = _nodeToInsert;
        }
    }

}

class Node {
    public int value;
    public Node next;

    public Node(int _value) {
        value = _value;
        next = null;
    }
}