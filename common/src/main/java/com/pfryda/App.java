package com.pfryda;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Scanner;
public class App
{
    public static void main( String[] args )
    {
        ICalc calculator= new Calculator();
        Logger logger = Logger.getLogger("Logs");
        FileHandler fileHandler;
        Scanner scanner = new Scanner(System.in);
        try {
            fileHandler = new FileHandler("/home/projects/uni/to/tocalc/logs.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        String expression;
        while  (true) {

            System.out.println("What do you want to calculate?" +
                    "\nEnter your expression: ");
            expression = scanner.nextLine();

            if(expression.contains("exit")){
                break;
            }

            try {
                System.out.println("\nResult: " + calculator.calculate(expression) + "\n");
            } catch (NumberFormatException  | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid expression"); }
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                logger.log(Level.SEVERE, e.getMessage(), e);
//            }
        }

        PluginProvider powClass = new PluginProvider(
                "/home/projects/uni/to/tocalc/plugins/pow.jar",
                "Pow",
                "pow"
        );
        PluginProvider sqrtClass = new PluginProvider(
                "/home/projects/uni/to/tocalc/plugins/sqrt.jar",
                "Sqrt",
                "sqrt"
        );
        Object powClassInstance = powClass.getInstance();
        Method pow = powClass.getMethod();

        Object sqrtClassInstance = sqrtClass.getInstance();
        Method sqrt = sqrtClass.getMethod();

        try {
            System.out.println(pow.invoke(powClassInstance, 2, 12));
            System.out.println(sqrt.invoke(sqrtClassInstance, 2));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
