package bloom_filter;

public class BloomFilter {
    public int filter_len;
    public int filter;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        filter = 0;
    }

    public int hash1(String str1) {
        int result = 0;
        for (int i = 0; i < str1.length(); i++) {
            result = (result * 17 + str1.charAt(i)) % filter_len;
        }

        return result;
    }

    public int hash2(String str1) {
        int result = 0;
        for (int i = 0; i < str1.length(); i++) {
            result = (result * 223 + str1.charAt(i)) % filter_len;
        }

        return result;
    }

    public void add(String str1) {
        int hashFilter1 = 1 << hash1(str1);
        int hashFilter2 = 1 << hash2(str1);

        filter = filter | hashFilter1 | hashFilter2;
    }

    public boolean isValue(String str1) {
        int hashMove1 = hash1(str1);
        int hashMove2 = hash2(str1);

        return (filter >> hashMove1 & 1) == 1 && (filter >> hashMove2 & 1) == 1;
    }
}