package recursion;

import java.util.ArrayList;

public class ListLength<T> {
    public int getLength(List<T> list) {
        if (list.isEmpty()) {
            return 0;
        }

        list.pop();
        return 1 + getLength(list);
    }
}

class List<T> {
    private ArrayList<T> _list;

    public List(ArrayList<T> list) {
        _list = list;
    }

    public void pop() {
        _list.remove(0);
    }

    public boolean isEmpty() {
        return _list.isEmpty();
    }
}