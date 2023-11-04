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

        for (int i = 0; i < value.length(); i++) {
            charSum += value.charAt(i) * (i + 1);
        }

        return (int) charSum % size;
    }

    public int seekSlot(String value) {
        int slot = hashFun(value);

        if (slots[slot] == null || Objects.equals(slots[slot], value)) {
            return slot;
        }

        String currentValue = slots[slot];
        slot = (slot + step) % size;
        while (slots[slot] != null && !Objects.equals(slots[slot], value) && !Objects.equals(slots[slot], currentValue)) {
            slot = (slot + step) % size;
        }

        if (slots[slot] == null || Objects.equals(slots[slot], value)) {
            return slot;
        }
        return -1;
    }

    public int put(String value) {
        int slot = seekSlot(value);
        if (slot == -1) {
            return -1;
        }

        slots[slot] = value;
        return slot;
    }

    public int find(String value) {
        int slot = hashFun(value);
        if (Objects.equals(slots[slot], value)) {
            return slot;
        }

        String currentValue = slots[slot];
        slot = (slot + step) % size;
        while (slots[slot] != null && !Objects.equals(slots[slot], value) && !Objects.equals(slots[slot], currentValue)) {
            slot = (slot + step) % size;
        }

        if (Objects.equals(slots[slot], value)) {
            return slot;
        }

        return -1;
    }
}
