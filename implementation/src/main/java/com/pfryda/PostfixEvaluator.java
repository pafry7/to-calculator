package com.pfryda;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

import java.lang.reflect.*;
import org.apache.logging.log4j.*;

public class PostfixEvaluator extends Operators {

    private static Logger fileLogger = LogManager.getLogger("PostfixEvaluator");

    public static Double evaluate(ArrayList<String> postfix)  throws EmptyStackException{
        Stack<Double> operands = new Stack<>();

        for (String token: postfix) {
            if (isNumeric(token)) {
                operands.push(Double.parseDouble(token));
            } else if (isTwoArgumentOperator(token)) {
                try {
                    Double rightOperand = operands.pop();
                    Double leftOperand = operands.pop();
                    Double result = calculateTwoArg(token, leftOperand, rightOperand);
                    operands.push(result);
                } catch (EmptyStackException e){
                    System.out.println("Invalid expression, please enter proper input\n");
                    fileLogger.log(Level.getLevel("ERROR"),  e);
                }

            } else if (isFunction(token)) {
                String classToLoad = token.substring(0, 1).toUpperCase() + token.substring(1);
                String methodToLoad = token.substring(0, 1) + token.substring(1);
                try {
                    // capitalize first letter to reflect class name spelling
                    PluginProvider plugin = new PluginProvider(
                            "/home/projects/uni/to/tocalc/plugins/",
                            classToLoad,
                            methodToLoad
                    );
                    Object classInstance = plugin.getInstance();
                    Method method = plugin.getMethod();

                    Object functionResult = method.invoke(classInstance, operands.pop());
                    operands.push((Double) functionResult);
                } catch (IllegalAccessException | InvocationTargetException | NullPointerException | EmptyStackException e) {
                    fileLogger.log(Level.getLevel("ERROR"), e);
                }
            }        }

        return operands.pop();
    }
}
