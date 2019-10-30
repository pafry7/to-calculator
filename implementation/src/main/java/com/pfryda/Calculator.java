package com.pfryda;

public class Calculator  implements ICalc {

    @Override
    public double add(double ...numbers) {
        double sum = 0;
        for(double number : numbers){
            sum += number;
        }
        return sum;
    }

    @Override
    public double substract(double a, double b) {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    @Override
    public double divide(double a, double b) {
        if (b != 0){
            return a / b;
        } else {
            System.out.println("Cannot divide 0");

        }
        return 0;
    }
}
