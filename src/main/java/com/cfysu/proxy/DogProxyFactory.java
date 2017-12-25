package com.cfysu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理，被代理类必须实现接口
 */
public class DogProxyFactory implements InvocationHandler{

    private Object target;

    public Object createProxy(Object target){
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
