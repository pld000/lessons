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

        while(slots[slot] != null && !Objects.equals(slots[slot], currentValue)) {
            slot = (slot + _step) % _size;
        }

        return slot;
    }

    public int _hashFun(String value) {
        double charSum = 0;
//        int k = value.length() * 2 + 1;
        var k = 31;
        double mod = 1e9+7;
        for (int i = 0; i < value.length(); i++) {
          //  charSum += value.charAt(i) * (i + 1);
            // charSum += (int) value.charAt(i) * Math.pow(31, i);
            int x = (value.charAt(i) - 'a' + 1);
            h = (h * k + x) % mod;
        }

        return (int) charSum % (int) 1e9+7;
    }
}
