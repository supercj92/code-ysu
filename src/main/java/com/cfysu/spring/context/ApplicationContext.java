package com.cfysu.spring.context;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author canglong
 * @Date 2018/10/30
 */
public class ApplicationContext {

    @Test
    public void testAnnotationApplicationContext(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //applicationContext.register(ConfigBean.class);
        applicationContext.scan("com.cfysu.spring.context");
        applicationContext.refresh();
        ConfigService configService = applicationContext.getBean(ConfigService.class);
        System.out.println("==done==");
    }
}
