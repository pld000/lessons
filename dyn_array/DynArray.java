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
        T[] _array = (T[]) Array.newInstance(this.clazz, new_capacity);

        if (this.count > 0) {
            int length = Math.min(_array.length, this.array.length);
            for (int i = 0; i < length; i++) {
                _array[i] = this.array[i];
            }
        }
        this.array = _array;
    }

    public T getItem(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        } else {
            return this.array[index];
        }
    }

    public void append(T itm) {
        if (this.count == this.capacity) {
            this._extendArray();
        }

        this.array[this.count] = itm;
        this.count++;
    }

    public void insert(T itm, int index) {
        if (index < 0 || index > this.count) {
            throw new IndexOutOfBoundsException();
        }

        if (this.count == this.capacity) {
            this._extendArray();
        }

        for (int i = this.count - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }

        this.array[index] = itm;
        this.count++;
    }

    public void remove(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        }

        if (index == this.count - 1) {
            this.array[index] = null;
        } else {
            for (int i = index; i < this.count - 1; i++) {
                this.array[i] = this.array[i + 1];
            }

            this.array[this.count - 1] = null;
        }

        this.count--;

        int new_capacity = this._getReducedCapacity();

        if (new_capacity != -1 && new_capacity != this.capacity) {
            this.makeArray(new_capacity);
        }
    }

    private int _getReducedCapacity() {
        int reducedCapacity = (int) (this.capacity / 1.5);
        return this.count <= reducedCapacity ? Math.max(reducedCapacity, 16) : -1;
    }

    private void _extendArray() {
        int new_capacity = this.array.length * 2;
        this.makeArray(new_capacity);
    }

}
