package power_set;

import java.util.Objects;

public class PowerSet {
    public String[] slots;
    private final int _step = 3;
    private final int _size = 20000;
    private int _capacity = 0;

    public PowerSet() {
        slots = new String[_size];
    }

    public int size() {
        return _capacity;
    }


    public void put(String value) {
        _capacity++;
    }

    public boolean get(String value) {
        // возвращает true если value имеется в множестве,
        // иначе false
        return false;
    }

    public boolean remove(String value) {
        // возвращает true если value удалено
        // иначе false
        _capacity--;
        return false;
    }

    public PowerSet intersection(PowerSet set2) {
        // пересечение текущего множества и set2
        return null;
    }

    public PowerSet union(PowerSet set2) {
        // объединение текущего множества и set2
        return null;
    }

    public PowerSet difference(PowerSet set2) {
        // разница текущего множества и set2
        return null;
    }

    public boolean isSubset(PowerSet set2) {
        // возвращает true, если set2 есть
        // подмножество текущего множества,
        // иначе false
        return false;
    }

    public int _seekSlot(String value) {
        int slot = _hashFun(value);
        String currentValue = slots[slot];

        while (slots[slot] != null && !Objects.equals(slots[slot], currentValue)) {
            slot = (slot + _step) % _size;
        }

        return slot;
    }

    public int _hashFun(String value) {
        long charSum = 0;
//        int k = value.length() * 2 + 1;
        int k = 131;
//        var k = 10;
        for (int i = 0; i < value.length(); i++) {
             // charSum += (long) value.charAt(i) * (i + 1);
//             charSum += (int) value.charAt(i) * ((long) k * i + 1);
             charSum += value.charAt(i) * ((long) Math.pow(k, i));
        }

        return (int) charSum % _size;
    }
}
