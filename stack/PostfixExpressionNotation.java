package stack;

public class PostfixExpressionNotation {
    public Stack<Character> expressionStack;
    public Stack<Integer> resultStack;

    public PostfixExpressionNotation() {
        this.expressionStack = new Stack<Character>();
        this.resultStack = new Stack<Integer>();
    }

    public void pushExpression(String expression) {
        for (int i = expression.length() - 1; i <= 0; i--) {
            this.expressionStack.push(expression.charAt(i));
        }
    }
}
