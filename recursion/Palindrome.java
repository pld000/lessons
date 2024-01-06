package recursion;

public class Palindrome {
    public static boolean check(String str, int currentIndex) {
        if (currentIndex >= str.length() / 2) {
            return true;
        }

        return str.charAt(currentIndex) == str.charAt(str.length() - ++currentIndex) && check(str, currentIndex);
    }

    public static boolean check(String str) {
        return check(str, 0);
    }
}
