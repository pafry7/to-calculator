package com.pfryda;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Scanner;
public class App
{
    public static void main(String[] args )
    {
        ICalc calculator= Calculator.getInstance();
        calculator.start();
    }
}
