package stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostfixExpressionNotationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void pushExpression() {
        PostfixExpressionNotation postfixNotation = new PostfixExpressionNotation();
        String expression = "8 2 + 5 * 9 + =";
        postfixNotation.pushExpression(expression);
        int lengthWithoutSpace = 7;

        Assertions.assertEquals(lengthWithoutSpace, postfixNotation.expressionStack.size(), "Failed pushExpression, wrong size");
    }

    @Test
    void executeExpression() {
        PostfixExpressionNotation postfixNotation = new PostfixExpressionNotation();

        String expression2 = "8 2 + 5 * 9 + =";
        postfixNotation.pushExpression(expression2);

        Assertions.assertEquals(59, postfixNotation.executeExpression(), "Failed executeExpression, wrong peek result");
    }
}