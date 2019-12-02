package com.cfysu.spring.aop.factorybean;

import com.cfysu.proxy.jdk.Dog;
import com.cfysu.proxy.jdk.DogImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Author canglong
 * @Date 2019/11/29
 */
public class ProxyFactoryMain {
    public static void main(String[] args) {
        Dog dog = new DogImpl();
        ProxyFactory proxyFactory = new ProxyFactory(dog);
        proxyFactory.addAdvice(new DemoBeforeAdvice());
        Dog proxyDog = (Dog) proxyFactory.getProxy();
        proxyDog.bark("ProxyFactory");
    }
}
