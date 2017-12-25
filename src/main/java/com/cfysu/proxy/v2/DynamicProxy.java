package com.cfysu.proxy.v2;

import com.cfysu.proxy.Dog;
import com.cfysu.proxy.DogImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String[] args){
        new DynamicProxy().test();
    }

    public void test(){
        Dog smallDog = new DogImpl();
        DogHandler handler = new DogHandler(smallDog);
        Dog dogProxy = (Dog) Proxy.newProxyInstance(smallDog.getClass().getClassLoader(),new Class[]{Dog.class},handler);
        dogProxy.eat("狗粮");
        dogProxy.bark("hehe");
    }

    private class DogHandler implements InvocationHandler{

        private Object target;

        private DogHandler(Object target){
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("before...");
            method.invoke(target, args);
            System.out.println("after...");

            return null;
        }
    }
}
