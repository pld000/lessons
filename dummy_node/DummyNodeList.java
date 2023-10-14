package dummy_node;
import java.util.*;

public class DummyNodeList {
    private Node _head;
    private Node _tail;

    public DummyNodeList() {
        _head = new Node();
        _tail = new Node();

        _head.next = _tail;
        _tail.prev = _head;
    }

    public void add(Node node) {
        node.next = this._tail;
        node.prev = this._tail.prev;
        this._tail.prev.next = node;
        this._tail.prev = node;
    }

    public void add(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null) {
            _nodeAfter = this._head;
        }

        _nodeAfter.next.prev = _nodeToInsert;
        _nodeToInsert.next = _nodeAfter.next;
        _nodeToInsert.prev = _nodeAfter;

        _nodeAfter.next = _nodeToInsert;
    }

    public boolean remove(int _value) {
        Node node = this._head.next;

        while(node != null) {
            if (node.value == _value) {
                node.next.prev = node.prev;
                node.prev.next = node.next;

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

    public int count() {
        int result = 0;
        Node node = this._head.next;
        while (node != null && !node.isDummy) {
            result++;
            node = node.next;
        }

        return result;
    }
}

class Node {
    public int value;
    public Node next;
    public Node prev;
    public boolean isDummy;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
        isDummy = false;
    }

    public Node() {
        next = null;
        prev = null;
        isDummy = true;
    }
}