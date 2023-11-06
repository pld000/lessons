package dictionary;

import java.lang.reflect.Array;
import java.util.Objects;

class NativeDictionary<T> {
    public int size;
    public String[] slots;
    public T[] values;

    private final int _step = 3;

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key) {
        double charSum = 0;
        for (int i = 0; i < key.length(); i++) {
            charSum += key.charAt(i) * (i + 1);
        }

        return (int) charSum % size;
    }

    public boolean isKey(String key) {
        int slot = hashFun(key);
        if (Objects.equals(slots[slot], key)) {
            return true;
        }

        String currentValue = slots[slot];
        slot = (slot + _step) % size;
        while (slots[slot] != null && !Objects.equals(slots[slot], key) && !Objects.equals(slots[slot], currentValue)) {
            slot = (slot + _step) % size;
        }

        return Objects.equals(slots[slot], key);
    }

    public void put(String key, T value) {
        int slot = _seekSlot(key);

        if (slot == -1) {
            return;
        }

        slots[slot] = key;
        values[slot] = value;
    }

    public T get(String key) {
        int slot = hashFun(key);
        if (Objects.equals(slots[slot], key)) {
            return values[slot];
        }

        String currentValue = slots[slot];
        slot = (slot + _step) % size;
        while (slots[slot] != null && !Objects.equals(slots[slot], key) && !Objects.equals(slots[slot], currentValue)) {
            slot = (slot + _step) % size;
        }

        return Objects.equals(slots[slot], key) ? values[slot] : null;
    }

    private int _seekSlot(String value) {
        int slot = hashFun(value);

        if (slots[slot] == null || Objects.equals(slots[slot], value)) {
            return slot;
        }

        String currentValue = slots[slot];
        slot = (slot + _step) % size;
        while (slots[slot] != null && !Objects.equals(slots[slot], value) && !Objects.equals(slots[slot], currentValue)) {
            slot = (slot + _step) % size;
        }

        return slots[slot] == null || Objects.equals(slots[slot], value) ? slot : -1;
    }
}