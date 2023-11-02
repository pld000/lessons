package hashing;

import java.util.Objects;

public class HashTable {
    public int size;
    public int step;
    public String[] slots;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    public int hashFun(String value) {
        double charSum = 0;
        int k = value.length();

        for (int i = 0; i < k; i++) {
            charSum += value.charAt(i) * Math.pow(k * 2, i);
        }

        return (int) charSum % size;
    }

    public int seekSlot(String value) {
        int slot = hashFun(value);

        int k = -slot;
        while (k <= size - 1) {
            if (slots[slot] == null) {
                break;
            }

            slot += step;
            slot = slot <= size - 1 ? slot : 0;
            k += step;
        }

        if (slots[slot] == null) {
            return slot;
        }

        return -1;
    }

    public int put(String value) {
        if (find(value) >= 0) {
            return -1;
        }

        int slot = seekSlot(value);

        if (slot == -1) {
            return -1;
        }

        slots[slot] = value;
        return slot;
    }

    public int find(String value) {
        int slot = hashFun(value);

        int k = -slot;
        while (k <= size - 1) {
            if (Objects.equals(slots[slot], value)) {
                break;
            }

            slot += step;
            slot = slot <= size - 1 ? slot : 0;
            k += step;
        }

        if (Objects.equals(slots[slot], value)) {
            return slot;
        }

        return -1;
    }
}
