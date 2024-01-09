package recursion;

public class Palindrome {
    public static boolean check(String str) {
        return _check(str, 0);
    }

    private static boolean _check(String str, int startIndex) {
        if (startIndex >= str.length() / 2) {
            return true;
        }

        int endIndex = str.length() - 1 - startIndex;
        if (str.charAt(startIndex) != str.charAt(endIndex)) {
            return false;
        }

        return _check(str, startIndex + 1);
    }
}
