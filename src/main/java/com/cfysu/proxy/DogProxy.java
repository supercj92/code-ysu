package com.cfysu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DogProxy implements InvocationHandler{

    private Object target;

    public Object bind(Object target){
        this.target = target;

        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("张嘴。。。");
        method.invoke(target, args);
        System.out.println("闭嘴。。。");
        return null;
    }
}