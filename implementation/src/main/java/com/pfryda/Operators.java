package com.pfryda;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Operators {

    private static Logger myLogger = LogManager.getLogger("Operators");

    private static List<String> supportedOperators = Arrays.asList("+", "-", "*", "/");
    private static List<String> twoArgumentOperators = Arrays.asList("+", "-", "*", "/");
    private static Map<String, Integer> operatorPrecedence = new HashMap<String, Integer>() {
        {
            put("*", 3);
            put("/", 3);
            put("+", 2);
            put("-", 2);
            put("(", 1);
            put(")", 0);
        }
    };

    public static boolean isNumeric(String str) {
        try {
            Double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean isOperator(String str) {
        return supportedOperators.contains(str);
    }

    public static boolean isTwoArgumentOperator(String str) {
        return twoArgumentOperators.contains(str);
    }

    public static boolean isFunction(String str) {
        return str.matches("[A-Za-z]+");
    }

    public static Integer getPrecedence(String op) {
        return operatorPrecedence.get(op);
    }

    public static Double calculateTwoArg(String operator, Double leftOperand, Double rightOperand) {
        char op = operator.charAt(0);
        double result = 0.0;

        switch(op) {
            case '+':
                result = leftOperand + rightOperand;
                break;
            case '-':
                result = leftOperand - rightOperand;
                break;
            case '*':
                result = leftOperand * rightOperand;
                break;
            case '/':
                try {
                    if (rightOperand == 0) {
                        throw new IllegalArgumentException();
                    }
                    result = leftOperand / rightOperand;
                    break;
                } catch (IllegalArgumentException e) {
                    myLogger.log(Level.getLevel("INFO"), "Division by 0");
                    System.out.println("Don't divide by 0!");
                    return null;
                }
            default:
                System.out.println("Unsupported operator " + op);
                break;
        }

        return result;
    }
}
