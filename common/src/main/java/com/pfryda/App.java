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

        File dir = new File("/home/projects/uni/to/plugins");
        URL loadPath = null;
        try {
            loadPath = dir.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URL[] classUrl = new URL[]{loadPath};

        ClassLoader cl = new URLClassLoader(classUrl);

        try {
            Class loadedClass = cl.loadClass("TestClass"); // m
            System.out.println(loadedClass.getName() + " exists!");

            Object b = loadedClass.newInstance();
            Method x = loadedClass.getMethod("A");
             x.invoke(b);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
