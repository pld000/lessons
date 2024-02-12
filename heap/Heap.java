package heap;

import java.util.*;

class Heap {
    public int[] HeapArray;
    private int _count;

    public Heap() {
        HeapArray = null;
        _count = 0;
    }

    public void MakeHeap(int[] a, int depth) {
        int size = (int) (Math.pow(2, depth + 1) - 1);
        HeapArray = new int[size];

        for (int i = 0; i < HeapArray.length; i++) {
            Add(a[i]);
        }
    }

    public int GetMax() {
        _count--;
        // вернуть значение корня и перестроить кучу
        return -1; // если куча пуста
    }

    public boolean Add(int key) {
        if (HeapArray == null || HeapArray.length == _count) {
            return false;
        }

        HeapArray[_count] = key;
        int indexOffset = _count % 2 == 0 ? 2 : 1;
        int parentIndex = Math.max(_count - indexOffset, 0) / 2;

        if (HeapArray[parentIndex] < HeapArray[_count]) {

        }

        _count++;
        return true;
    }

}