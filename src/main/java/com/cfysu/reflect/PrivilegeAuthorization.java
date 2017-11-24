package com.cfysu.reflect;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class PrivilegeAuthorization {

    public static void main(String[] args) throws NoSuchMethodException {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
        PrivilegeAuthorization auth = (PrivilegeAuthorization)beanFactory.getBean("privilegeAuthorization");
        //auth.doSomeThing();
        auth.doSomeThing2();
    }

    @Authorization("admin")
    public void doSomeThing(){
        System.out.println("admin do sth...");
    }

    @Authorization("user")
    public void doSomeThing2(){
        System.out.println("user do sth...");
    }
}
