package dyn_array;

import java.lang.reflect.Array;

public class DynArray<T> {
    public T[] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz) {
        clazz = clz;
        // new DynArray<Integer>(Integer.class);

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity) {
        if (new_capacity < 16) {
            new_capacity = 16;
        }
        this.capacity = new_capacity;
        this.array = (T[]) Array.newInstance(this.clazz, new_capacity);
    }

    public T getItem(int index) {
        if (index < 0 || index >= this.capacity) {
            throw new IndexOutOfBoundsException();
        } else {
            return this.array[index];
        }
    }

    public void append(T itm) {
        this.count++;
        if (this.count == this.capacity) {
            this._extendArray();
        }

        this.array[this.count] = itm;
    }

    public void insert(T itm, int index) {
        if (index < 0 || index >= this.capacity) {
            throw new IndexOutOfBoundsException();
        } else {
            this.count++;
            if (this.count == this.capacity) {
                this._extendArray();
            }

            for (int i = this.count; i >= index; i--) {
                this.array[i + 1] = this.array[i];
            }
            this.array[index] = itm;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= this.capacity) {
            throw new IndexOutOfBoundsException();
        } else {
            this.array[index] = null;
            this.count--;

        }
    }

    private boolean _canBeReduced() {
        int reduceFrom = this._getReduceIndex();
        boolean allItemsIsEmpty = true;

        for (int i = this.capacity; i >= reduceFrom; i++) {
            allItemsIsEmpty = allItemsIsEmpty && this.array[i] == null;
        }

        return allItemsIsEmpty;
    }

    private int _getReduceIndex() {
        int reduceFrom = (int) Math.round(this.capacity / 1.5);
        return Math.max(reduceFrom, 16);
    }

    private void _extendArray() {
        int capacity = this.array.length * 2;
        T[] _array = this.array;
        this.makeArray(capacity);

        for (int i = 0; i < _array.length; i++) {
            this.array[i] = _array[i];
        }
    }

}
