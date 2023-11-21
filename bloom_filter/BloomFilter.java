package bloom_filter;

public class BloomFilter {
    public int filter_len;
    public int filter;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        filter = 0;
    }

    public int hash1(String str1) {
        // result - hashForString
        int hashForString = 0;
        // значение хеш функции для строки
        for (int i = 0; i < str1.length(); i++) {
            hashForString = (hashForString * 17 + str1.charAt(i)) % filter_len;
        }

        return hashForString;
    }

    public int hash2(String str1) {
        // result - hashForString
        int hashForString = 0;
        // значение хеш функции для строки
        for (int i = 0; i < str1.length(); i++) {
            hashForString = (hashForString * 223 + str1.charAt(i)) % filter_len;
        }

        return hashForString;
    }

    public void add(String str1) {
        // hashFilter1 - elementFilterPosition1
        int elementFilterPosition1 = 1 << hash1(str1);
        // первая позиция строкового элемента в фильтре блюма

        // hashFilter2 - elementFilterPosition2
        int elementFilterPosition2 = 1 << hash2(str1);
        // вторая позиция строкового элемента в фильтре блюма

        filter = filter | elementFilterPosition1 | elementFilterPosition2;
    }

    public boolean isValue(String str1) {
        // hashMove1 - elementFilterOffset1
        int elementFilterOffset1 = hash1(str1);
        // первый сдвиг фильтра блюма для определения наличия элемента

        // hashMove2 - elementFilterOffset2
        int elementFilterOffset2 = hash2(str1);
        // второй сдвиг фильтра блюма для определения наличия элемента

        return (filter >> elementFilterOffset1 & 1) == 1 && (filter >> elementFilterOffset2 & 1) == 1;
    }

    public void doNothing() {
    }
}