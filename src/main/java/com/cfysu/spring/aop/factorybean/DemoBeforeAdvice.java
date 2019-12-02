package com.cfysu.spring.aop.factorybean;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2019/11/29
 */
@Component
public class DemoBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before " +  method.getName() + " do sth ...");
    }
}
