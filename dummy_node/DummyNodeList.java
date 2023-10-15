package dummy_node;
import java.util.*;

public class DummyNodeList {
    public BaseNode<Node> _head;
    public BaseNode<Node> _tail;

    public DummyNodeList() {
        _head = new BaseNode<Node>();
        _tail = new BaseNode<Node>();

        _head.next = (Node) _tail;
        _tail.prev = (Node) _head;
    }

    public void add(Node node) {
        node.next = (Node) this._tail;
        node.prev = this._tail.prev;
        this._tail.prev.next = node;
        this._tail.prev = node;
    }

    public void add(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null) {
            _nodeAfter = (Node) this._head;
        }

        _nodeAfter.next.prev = _nodeToInsert;
        _nodeToInsert.next = _nodeAfter.next;
        _nodeToInsert.prev = _nodeAfter;

        _nodeAfter.next = _nodeToInsert;
    }

    public boolean remove(int _value) {
        Node node = this._head.next;

        while (node != null) {
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
        while (node != null && node instanceof Node) {
            result++;
            node = node.next;
        }

        return result;
    }
}

class BaseNode<N> {
    public N next;
    public N prev;
    public int value = 0;

    public BaseNode() {
        next = null;
        prev = null;
    }
}

class Node extends BaseNode<Node> {
    public Node(int _value) {
        super();
        value = _value;
    }
}