package com.cfysu.jvm.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class CustomClassloader extends URLClassLoader{

    public CustomClassloader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public CustomClassloader(URL[] urls) {
        super(urls);
    }

    public CustomClassloader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public static void main(String[] args){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        //app
        System.out.println(contextClassLoader);
        System.out.println(System.getProperty("java.class.path"));

        //ext
        System.out.println(contextClassLoader.getParent());
        System.out.println(System.getProperty("java.ext.dirs"));

        //bootstrap
        System.out.println(contextClassLoader.getParent().getParent());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
