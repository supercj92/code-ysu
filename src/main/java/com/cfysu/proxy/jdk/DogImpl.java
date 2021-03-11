package com.cfysu.proxy.jdk;

import org.springframework.stereotype.Component;

@Component
public class DogImpl implements Dog{

    /**
     * 1、pointcut是指哪些方法要被代理，是一个过滤规则
     * 2、joinpoint是指被代理的方法，已经通过了ponitcut的规则匹配
     * 3、advice是要执行的增强
     * 4、advisor是pointcut和joinpoint的组合体，spring所特有的
     */
    @Override
    public void bark(String str) {
        System.out.println("汪汪汪。。。" + str);
    }

    @Override
    public void eat(String food){
        System.out.println("dog eat " + food);
    }

    public static void main(String[] args) {
        System.out.println(Dog.class.isInterface());
    }
}
