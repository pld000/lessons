package stack;

public class BalancedBrackets {
    public static boolean checkBalance(String brackets) {
        Character closeBracket = ')';
        Character openBracket = '(';
        Stack<Character> stack = new Stack<Character>();
        Stack<Character> resultStack = new Stack<Character>();

        for (int i = 0; i < brackets.length(); i++) {
            stack.push(brackets.charAt(i));
        }

        while (stack.size() > 0) {
            Character bracket = stack.pop();

            if (bracket == openBracket && resultStack.peek() == closeBracket) {
                resultStack.pop();
            } else {
                resultStack.push(bracket);
            }
        }

        return resultStack.size() == 0;
    }

}
