package com.rabierre.calculator;

import com.rabierre.calculator.core.OperatorToken;
import com.rabierre.calculator.core.Token;
import com.rabierre.calculator.core.ValueToken;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 7.
 * Time: 오후 10:40
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {

    public Token run(List<Token> refined) {
        Stack<Token> stack = new Stack();

        for (Token token : refined) {
            // if variable or constant value
            if (token instanceof ValueToken) {
                stack.push(token);
                continue;
            }

            // calculation over
            if (stack.size() == 1) return token;

            // if operator

            // stack pop makes order revered, so first ValueToken is right operand
            // this order is important when operate division, remainder and power.
            ValueToken operand2 = (ValueToken) stack.pop();
            ValueToken operand1 = (ValueToken) stack.pop();

            OperatorToken operator = ((OperatorToken) token);

            ValueToken result = operator.calculate(operand1.getValue(), operand2.getValue());

            stack.push(result);
        }

        return stack.pop();
    }
}
