package queue;

import stack.Stack;

public class TwoStackQueue<T> {
    private Stack<T> _main;

    public TwoStackQueue() {
        _main = new Stack<>();
    }

    public void enqueue(T item) {
        Stack<T> _tmp = new Stack<>();

        while (_main.size() > 0) {
            _tmp.push(_main.pop());
        }

        _main.push(item);

        while (_tmp.size() > 0) {
            _main.push(_tmp.pop());
        }
    }

    public T dequeue() {
        return _main.pop();
    }

    public int size() {
        return _main.size();
    }
}

