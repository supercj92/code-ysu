package com.cfysu.spring.extend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2019/3/13
 */
@Component
@Slf4j
public class TestBean2 {
    public TestBean2(){
        log.info("init:TestBean2");
    }
}
