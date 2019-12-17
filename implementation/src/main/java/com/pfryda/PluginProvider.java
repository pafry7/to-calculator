package com.pfryda;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.*;

public class PluginProvider {

    private File pathToJar;
    private String functionName;
    private String className;
    private Class loadedClass;
    private Logger logger;

    public PluginProvider(String pathToJar, String className, String functionName){
        this.pathToJar = new File(pathToJar);
        this.functionName = functionName;
        this.className = className;
        this.loadedClass = this.getClassObject();
        this.logger = Logger.getLogger("Logs");
    }

    private Class getClassObject(){
        Class loadedClass = null;
        try {
            URL loadPath = this.pathToJar.toURI().toURL();
            URL[] classUrl = new URL[]{loadPath};

            ClassLoader cl = new URLClassLoader(classUrl);
            loadedClass = cl.loadClass(this.className);

        } catch (ClassNotFoundException | MalformedURLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return loadedClass;
    }

    public Object getInstance(){
        Object newInstance = null;
        try {
            newInstance = this.loadedClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return newInstance;
    }

    public Method getMethod(){
        Method method = null;
        Method[] methods = this.loadedClass.getMethods();
        for(Method function : methods) {
            if(function.getName().equals(this.functionName)){
                method = function;
            }
        }
        return method;
    }
}
