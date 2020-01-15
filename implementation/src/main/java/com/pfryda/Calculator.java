package com.pfryda;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.ArrayList;

public class Calculator  implements ICalc {

    private final static String EXIT_COMMAND = "quit";
    private static Calculator  instance =null;

    private Calculator (){

    }

    // singleton
    public static Calculator getInstance(){
        if(instance == null){
            instance = new Calculator();
        }
        return instance;
    }

    @Override
    public Double calculate(String expression){
        ArrayList<String> tokenizedExpression = ExpressionTokenizer.tokenize(expression);
        ArrayList<String> postfixExpression = ShuntingYard.infixToPostfix(tokenizedExpression);
        try {
            return PostfixEvaluator.evaluate(postfixExpression);
        }  catch (EmptyStackException ignored) {
        }
        return null;
    }



    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String expression;

        while (true) {

            System.out.println("What do you want to calculate?" +
                    "\nEnter your expression(quit to finish): ");

            expression = scanner.nextLine();

            if (expression.equals(EXIT_COMMAND)) {
                break;
            }

            Double result = calculate(expression);
            if (result == null){
                System.out.println("");
            } else {

                System.out.println("Result: " + result);
            }
        }

        scanner.close();

        System.out.println("\n*************************************\n" +
                "*                    Thank you.                  *\n" +
                "*              Happy  Weekend!             *\n" +
                "*************************************");
    }
}
