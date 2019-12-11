package com.pfryda;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class App
{
    public static void main( String[] args )
    {
        ICalc a = new Calculator();
        System.out.println(a.multiply(2,3));

        System.out.println("-------------\n\n");

        File dir = new File("/home/projects/uni/to/tocalc/plugins");

        URL loadPath = null;
        try {
            loadPath = dir.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URL[] classUrl = new URL[]{loadPath};
        ClassLoader cl =  new URLClassLoader(classUrl);
        try {
            Class loadedClass = cl.loadClass("TestClass");
            Object b = loadedClass.newInstance();

            Method[] methods = loadedClass.getMethods();
            for (Method func : methods) {
                if (func.getName().equals("sqrt")) {
                    double x = (Double)(func.invoke(b, 2));
                    System.out.println(x);
                }
            }
            System.out.println("\n\n\n--------------");

        } catch (ClassNotFoundException |IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
