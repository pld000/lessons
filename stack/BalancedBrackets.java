package stack;

public class BalancedBrackets {
    public static boolean checkBalance(String brackets) {
        int controlSum = 0;
        char closeBracket = ')';
        char openBracket = '(';
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < brackets.length(); i++) {
            stack.push(brackets.charAt(i));
        }

        while (stack.size() > 0) {
            Character bracket = stack.pop();

            if (controlSum == 0 && bracket != closeBracket) {
                return false;
            } else if (bracket == closeBracket) {
                controlSum++;
            } else if (bracket == openBracket) {
                controlSum--;
            }
        }

        return controlSum == 0;
    }

}
