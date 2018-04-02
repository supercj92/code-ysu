package com.cfysu.proxy.jdk;

public class ProxyTest {
    public static void main(String[] args){
        Dog bigDog = new DogImpl();
        Dog dogProxy = (Dog)new DogProxyFactory().createProxy(bigDog);
        dogProxy.bark("大狗叫");
        dogProxy.eat("骨头");
    }
}
