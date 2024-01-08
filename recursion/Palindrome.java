package recursion;

public class Palindrome {
    public static boolean check(String str, int startIndex) {
        if (startIndex >= str.length() / 2) {
            return true;
        }

        int endIndex = str.length() - 1 - startIndex;
        if (str.charAt(startIndex) != str.charAt(endIndex)) {
            return false;
        }

        return check(str, startIndex + 1);
    }

    public static boolean check(String str) {
        return check(str, 0);
    }
}
