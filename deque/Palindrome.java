package deque;

public class Palindrome {
    public static boolean check(String str) {
        boolean isPalindrome = true;
        Deque<Character> deque = _mapStringToDeque(str);
        int iterations = deque.size() / 2;

        for (int i = 0; i < iterations; i++) {
            char front = deque.removeFront();
            char tail = deque.removeTail();

            isPalindrome = isPalindrome && front == tail;
        }

        return isPalindrome;
    }

    private static Deque<Character> _mapStringToDeque(String str) {
        Deque<Character> deque = new Deque<>();
        str = str.toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            deque.addFront(str.charAt(i));
        }

        return deque;
    }
}
