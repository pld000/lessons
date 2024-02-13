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
            if (i < a.length) {
                Add(a[i]);
            }
        }
    }

    public int GetMax() {
        if (HeapArray == null || HeapArray.length == 0 || _isHeapEmpty()) {
            return -1;
        }

        int root = HeapArray[0];
        HeapArray[0] = HeapArray[HeapArray.length - 1];
        HeapArray[HeapArray.length - 1] = 0;

        _SiftDown(0);
        _count--;

        return root;
    }

    public boolean Add(int key) {
        if (HeapArray == null || HeapArray.length == _count) {
            return false;
        }

        HeapArray[_count] = key;

        _SiftUp(_count);
        _count++;

        return true;
    }

    private void _SiftDown(int index) {
        int leftInd = 2 * index + 1;
        int rightInd = 2 * index + 2;
        int maxInd = _getIndexMax(leftInd, rightInd);

        if (maxInd == -1 || index >= HeapArray.length || HeapArray[index] >= HeapArray[maxInd]) {
            return;
        }

        int transfer = HeapArray[index];
        HeapArray[index] = HeapArray[maxInd];
        HeapArray[maxInd] = transfer;

        _SiftDown(maxInd);
    }

    private int _getIndexMax(int aInd, int bInd) {
        if (aInd >= HeapArray.length || bInd >= HeapArray.length) {
            return -1;
        }

        if (HeapArray[aInd] < HeapArray[bInd]) {
            return bInd;
        }

        return aInd;
    }

    private boolean _isHeapEmpty() {
        for (int j : HeapArray) {
            if (j > 0) {
                return false;
            }
        }

        return true;
    }

    private void _SiftUp(int index) {
        int indexOffset = index % 2 == 0 ? 2 : 1;
        int parentIndex = (index - indexOffset) / 2;

        if (parentIndex < 0 || HeapArray[parentIndex] >= HeapArray[index]) {
            return;
        }

        int transfer = HeapArray[parentIndex];
        HeapArray[parentIndex] = HeapArray[index];
        HeapArray[index] = transfer;

        _SiftUp(parentIndex);
    }

}