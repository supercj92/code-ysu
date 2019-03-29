package com.cfysu.spring.extend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author canglong
 * @Date 2019/3/13
 */
@Slf4j
@Component
public class TestBean1 implements InitializingBean {
    public TestBean1(){
        log.info("construct:{}", "TestBean1");
    }

    @PostConstruct
    public void postConstruct(){
        log.info("----postConstruct----");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("-----afterPropertiesSet-----");
    }
}
