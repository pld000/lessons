package stack;

public class InverseStack<T> {
    private InverseStackNode<T> head;
    private InverseStackNode<T> tail;

    public InverseStack() {
        this.head = null;
        this.tail = null;
    }

    public int size() {
        int res = 0;
        InverseStackNode<T> node = this.head;

        while (node != null) {
            res++;
            node = node.next;
        }

        return res;
    }

    public T pop() {
        if (this.head != null) {
            T value = this.head.value;

            if (this.head.next == null) {
                this.tail = null;
                this.head = null;
            } else {
                this.head = this.head.next;
            }

            return value;
        }
        return null;
    }

    public void push(T val) {
        InverseStackNode<T> _item = new InverseStackNode<T>(val);

        if (this.tail == null) {
            this.tail = _item;
        } else {
            _item.next = this.head;
        }
        this.head = _item;
    }

    public T peek() {
        if (this.head != null) {
            return this.head.value;
        }
        return null;
    }
}

class InverseStackNode<T> {
    public InverseStackNode<T> next;
    public T value;

    public InverseStackNode(T _value) {
        this.next = null;
        this.value = _value;
    }
}