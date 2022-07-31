package com.cfysu.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author canglong
 * @Date 2019/7/12
 */
public class AroundLogMain {


    public static void main(String[] args) {
        //FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("file:/Users/chris/IdeaProjects/mine/code-ysu/target/classes/ApplicationContext.xml");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.cfysu.spring.aop");

        OpeService opeService = (OpeService) applicationContext.getBean("opeService");

        opeService.foo("arg1 foo");
    }
}
