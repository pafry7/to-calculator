package com.pfryda;

public class App
{
    public static void main( String[] args )
    {
        ICalc a = new Calculator();
        System.out.println(a.multiply(2,3));
    }
}
