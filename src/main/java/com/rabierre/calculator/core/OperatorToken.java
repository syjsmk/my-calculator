package com.rabierre.calculator.core;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 8.
 * Time: 오후 2:54
 * To change this template use File | Settings | File Templates.
 */
public class OperatorToken implements Token {
    private String value;
    private int priority;

    public OperatorToken(String value) {
        this.value = value;
        this.priority = orderPriority();
    }

    private int orderPriority() {
        switch (value) {
            case "(":
            case ")":
                return Priority.LOW.getValue();
            case "+":
            case "-":
                return Priority.MEDIUM.getValue();
            case "*":
            case "/":
            case "^":
                return Priority.HIGH.getValue();
            default:
                return Priority.NONE.getValue();
        }
    }

    public String getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperatorToken)) return false;

        OperatorToken that = (OperatorToken) o;

        if (priority != that.priority) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    public boolean isHighPriorityThan(Token peek) {
        if (!(peek instanceof OperatorToken)) return false; // or error?

        return this.priority >= ((OperatorToken) peek).getPriority();
    }

    public Token calculate(Token token1, Token token2) {
        String operand1 = token1.getValue();
        String operand2 = token2.getValue();

        double result;
        switch (value) {
            case "+":
                result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                break;
            case "-":
                result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                break;
            case "*":
                result = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                break;
            case "/":
                result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                break;
            case "^":
                result = Math.pow(Double.parseDouble(operand1), Double.parseDouble(operand2));
                break;
            default:
                throw new IllegalArgumentException("");
        }

        return new ValueToken(String.valueOf(result));
    }
}