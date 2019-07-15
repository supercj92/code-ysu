package com.cfysu.spring.aop;

import com.cfysu.spring.context.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2019/7/12
 */
public class AroundLogMain {


    public static void main(String[] args) {
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("file:/Users/chris/IdeaProjects/mine/code-ysu/target/classes/ApplicationContext.xml");

        OpeService opeService = (OpeService) applicationContext.getBean("opeService");

        opeService.foo("arg1 foo");
    }
}
