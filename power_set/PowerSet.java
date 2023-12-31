package power_set;

import java.util.*;

public class PowerSet {
    public String[] values;
    private final int _step = 3511;
    private final int _size = 20000;

    public PowerSet() {
        values = new String[_size];
    }

    public int size() {
        int res = 0;
        for (int i = 0; i < _size; i++) {
            if (values[i] != null) {
                res++;
            }
        }
        return res;
    }


    public void put(String value) {
        int slot = _seekSlot(value);

        if (Objects.equals(values[slot], value)) {
            return;
        }

        values[slot] = value;
    }

    public boolean get(String value) {
        return _findSlot(value) != -1;
    }

    public boolean remove(String value) {
        int slot = _findSlot(value);

        if (slot == -1) {
            return false;
        }

        values[slot] = null;
        return true;
    }

    public PowerSet intersection(PowerSet set2) {
        PowerSet resultSet = new PowerSet();

        if (size() == 0) {
            return resultSet;
        }

        for (String value : values) {
            if (value != null && set2.get(value)) {
                resultSet.put(value);
            }
        }

        return resultSet;
    }

    public PowerSet union(PowerSet set2) {
        PowerSet resultSet = new PowerSet();

        for (int i = 0; i < _size; i++) {
            if (values[i] != null) {
                resultSet.put(values[i]);
            }

            if (set2.values[i] != null) {
                resultSet.put(set2.values[i]);
            }
        }

        return resultSet;
    }

    public PowerSet difference(PowerSet set2) {
        PowerSet resultSet = new PowerSet();

        for (int i = 0; i < _size; i++) {
            String _value = values[i];
            if (_value != null && !set2.get(_value)) {
                resultSet.put(_value);
            }
        }

        return resultSet;
    }

    public boolean isSubset(PowerSet set2) {
        for (int i = 0; i < _size; i++) {
            String _value = set2.values[i];
            if (_value != null && !get(_value)) {
                return false;
            }
        }

        return true;
    }

    public int _seekSlot(String value) {
        int slot = _hashFun(value);
        String currentValue = values[slot];

        if (currentValue == null || Objects.equals(currentValue, value)) {
            return slot;
        }

        slot = (slot + _step) % _size;
        while (values[slot] != null && !Objects.equals(values[slot], currentValue) && !Objects.equals(values[slot], value)) {
            slot = (slot + _step) % _size;
        }
        return slot;
    }

    public int _hashFun(String value) {
        long charSum = 0;
        int k = value.length() * 2 + 1;

        for (int i = 0; i < value.length(); i++) {
            charSum += value.charAt(i) * ((long) k * i + 1);
        }

        return (int) charSum % _size;
    }

    public int _findSlot(String value) {
        int slot = _hashFun(value);

        if (Objects.equals(values[slot], value)) {
            return slot;
        }

        slot = (slot + _step) % _size;
        for (int i = 0; i < _size; i++) {
            if (Objects.equals(values[slot], value)) {
                return slot;
            }
            slot = (slot + _step) % _size;
        }

        return -1;
    }
}
