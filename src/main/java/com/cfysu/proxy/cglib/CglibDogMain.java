package com.cfysu.proxy.cglib;

import com.cfysu.proxy.jdk.DogImpl;

public class CglibDogMain {
    public static void main(String[] args){
        CglibProxy cglibProxy = new CglibProxy();
        DogImpl dogProxy = (DogImpl)cglibProxy.getProxy(DogImpl.class);
        dogProxy.eat("xxx");
    }
}
