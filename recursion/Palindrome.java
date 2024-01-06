package recursion;

public class Palindrome {
    public static boolean check(String str) {
        return str.length() <= 1 || str.charAt(0) == str.charAt(str.length() - 1) && check(str.substring(1, str.length() - 1));
    }
}
