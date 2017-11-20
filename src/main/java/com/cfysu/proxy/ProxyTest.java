package com.cfysu.proxy;

public class ProxyTest {
    public static void main(String[] args){
        DogProxy dogProxy = new DogProxy();
        Dog dog = (Dog)dogProxy.bind(new DogImpl());
        dog.bark("111");
    }
}
