package cache;

import java.lang.reflect.Array;
import java.util.Objects;

public class NativeCache<T> {
    public int size;
    public String[] slots;
    public T[] values;
    public int[] hits;

    public int _capacity;
    private final int _step;

    public NativeCache(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        hits = new int[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        _step = _getStep(sz);
    }

    public int hashFun(String key) {
        double charSum = 0;
        for (int i = 0; i < key.length(); i++) {
            charSum = (charSum + key.charAt(i) * Math.pow(_step, i)) % size;
        }

        return (int) charSum;
    }

    public void put(String key, T value) {
        int slot = _seekSlot(key);

        slots[slot] = key;
        values[slot] = value;
        _capacity = _capacity < size ? _capacity + 1 : _capacity;
    }

    public T get(String key) {
        int slot = hashFun(key);
        if (Objects.equals(slots[slot], key)) {
            hits[slot]++;
            return values[slot];
        }

        String currentValue = slots[slot];
        slot = (slot + _step) % size;
        while (slots[slot] != null && !Objects.equals(slots[slot], key) && !Objects.equals(slots[slot], currentValue)) {
            slot = (slot + _step) % size;
        }

        if (Objects.equals(slots[slot], key)) {
            hits[slot]++;
            return values[slot];
        }

        return null;
    }

    public int _seekSlot(String value) {
        int slot = hashFun(value);

        if (slots[slot] == null || Objects.equals(slots[slot], value)) {
            return slot;
        }

        int replacementSlot = -1;
        String currentValue = slots[slot];
        slot = (slot + _step) % size;
        while (slots[slot] != null && !Objects.equals(slots[slot], value) && !Objects.equals(slots[slot], currentValue)) {
            slot = (slot + _step) % size;
            replacementSlot = _getReplacementSlot(replacementSlot, slot);
        }

        return slots[slot] == null || Objects.equals(slots[slot], value) ? slot : replacementSlot;
    }

    public int _getStep(int size) {
        int step = size * 16 / 100;
        return step % 2 == 0 ? step + 1 : step;
    }

    public int _getReplacementSlot(int replacementSlot, int slot) {
        if (_capacity < size) {
            return -1;
        }

        replacementSlot = replacementSlot == -1 ? slot : replacementSlot;

        return hits[replacementSlot] - hits[slot] >= 0 ? slot : replacementSlot;
    }
}
