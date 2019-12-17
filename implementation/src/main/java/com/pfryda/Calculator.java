package com.pfryda;
import java.util.ArrayList;

public class Calculator  implements ICalc {

    @Override
    public double calculate(String expression){
        ArrayList<String> tokenizedExpression = ExpressionTokenizer.tokenize(expression);
        ArrayList<String> postfixExpression = ShuntingYard.infixToPostfix(tokenizedExpression);

        return PostfixEvaluator.evaluate(postfixExpression);

    }
}
