package com.pfryda;
public class App
{
    public static void main(String[] args )
    {
        ICalc calculator= Calculator.getInstance();
        calculator.start();
    }
}
