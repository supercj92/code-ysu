package com.cfysu.proxy.spring;

import java.lang.reflect.Method;

import com.cfysu.proxy.jdk.Dog;
import com.cfysu.proxy.jdk.DogImpl;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Author canglong
 * @Date 2022/11/14
 */
@Slf4j
public class SpringAopMain {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new Class[] {Dog.class});
        proxyFactory.setTarget(new DogImpl());
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                log.info("before " + method.getName());
            }
        });
        proxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {

                log.info("around before");
                Object proceed = methodInvocation.proceed();
                log.info("around after");
                return proceed;
            }
        });

        Dog proxy = (Dog)proxyFactory.getProxy();
        proxy.eat("bread");
    }
}
