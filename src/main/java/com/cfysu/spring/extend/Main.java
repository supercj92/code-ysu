package com.cfysu.spring.extend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author canglong
 * @Date 2019/3/13
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.cfysu.spring.extend");
        log.info("bean :{}", applicationContext.getBeanFactory().getBeanDefinitionNames());
    }
}
