package queue;

import stack.Stack;

public class TwoStackQueue<T> {
    private Stack<T> _containerStack;
    private Stack<T> _giverStack;

    public TwoStackQueue() {
        _containerStack = new Stack<>();
        _giverStack = new Stack<>();
    }

    public void enqueue(T item) {
        _containerStack.push(item);
    }

    public T dequeue() {
        if (_giverStack.size() == 0) {
            while (_containerStack.size() > 0) {
                _giverStack.push(_containerStack.pop());
            }
        }

        return _giverStack.pop();
    }

    public int size() {
        return _containerStack.size() + _giverStack.size();
    }
}

