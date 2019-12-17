package com.pfryda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App
{
    public static void main( String[] args )
    {
        ICalc a = new Calculator();
        PluginProvider test = new PluginProvider(
                "/home/projects/uni/to/tocalc/plugins/pow.jar",
                "Pow",
                "pow"
        );
        Object haha = test.getInstance();
        Method xD = test.getMethod();
        try {
            System.out.println(xD.invoke(haha, 2, 12));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
