package com.pfryda;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.logging.log4j.*;

public class PluginProvider {

    private File pathToJar;
    private String functionName;

    private static Logger fileLogger = LogManager.getLogger("PluginProvider");

    private String className;
    private Class loadedClass;

    public PluginProvider(String pathToJar, String className, String functionName)  {
        this.pathToJar = new File(pathToJar + functionName + ".jar");
        this.functionName = functionName;
        this.className = className;
        this.loadedClass = this.getClassObject();
    }

    private Class getClassObject(){
        Class loadedClass = null;
        try {
            URL loadPath = this.pathToJar.toURI().toURL();
            URL[] classUrl = new URL[]{loadPath};

            ClassLoader cl = new URLClassLoader(classUrl);
            loadedClass = cl.loadClass(this.className);

        } catch (Exception e) {
            fileLogger.log(Level.getLevel("ERROR"), e);
        }
        return loadedClass;
    }

    public Object getInstance(){
        Object newInstance = null;
        try {
            newInstance = this.loadedClass.newInstance();
        } catch (Exception e) {
            fileLogger.log(Level.getLevel("ERROR"),  e);
        }
        return newInstance;
    }

    public Method getMethod(){
        Method method = null;
        try {
            Method[] methods = this.loadedClass.getMethods();
            for (Method function : methods) {
                if (function.getName().equals(this.functionName)) {
                    method = function;
                }
            }
        } catch (Exception e){
            fileLogger.log(Level.getLevel("ERROR"), e);
        }
        return method;
    }
}
