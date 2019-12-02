package com.cfysu.spring.aop.factorybean;

import com.cfysu.proxy.jdk.Dog;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author canglong
 * @Date 2019/11/29
 */
public class ProxyFactoryBeanMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.cfysu.spring.aop.factorybean","com.cfysu.proxy.jdk");
        Dog dog = applicationContext.getBean(Dog.class);

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setBeanFactory(applicationContext);
        proxyFactoryBean.setInterceptorNames("demoBeforeAdvice");
        proxyFactoryBean.setInterfaces(Dog.class);
        proxyFactoryBean.setTarget(dog);
        Dog proxyDog = (Dog)proxyFactoryBean.getObject();

        proxyDog.bark("sth");
    }
}
