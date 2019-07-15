package com.cfysu.spring.strategy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author canglong
 * @Date 2019/7/15
 */
public class ProcessorMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.cfysu.spring.strategy");
        applicationContext.refresh();

        ProcessorFactory processorFactory = (ProcessorFactory) applicationContext.getBean("processorFactory");
        Processor bizOne = processorFactory.getProcessor("bizOne");
        bizOne.process();
    }
}
