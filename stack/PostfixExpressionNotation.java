package stack;

import java.util.Arrays;

public class PostfixExpressionNotation {
    public Stack<Character> expressionStack;
    public Stack<Integer> resultStack;
    private final Character[] _operations = {'+', '-', '*'};
    private final int _delimiter = 32;

    public PostfixExpressionNotation() {
        this.expressionStack = new Stack<Character>();
        this.resultStack = new Stack<Integer>();
    }

    public void pushExpression(String expression) {
        for (int i = expression.length() - 1; i >= 0; i--) {
            char value = expression.charAt(i);

            if ((int) value != (int) ' ') {
                this.expressionStack.push(expression.charAt(i));
            }
        }
    }

    public void executeExpression() {
        while (this.expressionStack.size() > 0) {
            char value = this.expressionStack.pop();

            if (Arrays.asList(this._operations).contains(value)) {
                int operand1 = this.resultStack.pop();
                int operand2 = this.resultStack.pop();

                this.resultStack.push(this._execute(operand1, operand2, value));
            } else {
                this.resultStack.push(Character.getNumericValue(value));
            }
        }
    }

    private int _execute(int operand1, int operand2, char operation) {
        switch (operation) {
            case (int) '+':
                return operand1 + operand2;
            case (int) '-':
                return operand1 - operand2;
            case (int) '*':
                return operand1 * operand2;
        }

        return 0;
    }
}
