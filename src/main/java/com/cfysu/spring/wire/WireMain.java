package com.cfysu.spring.wire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author canglong
 * @Date 2022/7/14
 */
public class WireMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.cfysu.spring.wire");
        DemoService demoService = applicationContext.getBean(DemoService.class);
        System.out.println();
    }
}
