package com.cfysu.jvm;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class Classloader extends URLClassLoader{

    public Classloader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public Classloader(URL[] urls) {
        super(urls);
    }

    public Classloader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public static void main(String[] args){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader.getParent());
        System.out.println(contextClassLoader.getParent().getParent());
        System.out.println(contextClassLoader.getParent().getParent().getParent());
    }
}
