package com.cfysu.spring.strategy;

import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2019/7/15
 */
@Component
public class BizOneProcessor implements Processor {
    @Override
    public void process() {
        System.out.println(this.getClass().getName());
    }

    @Override
    public String getType() {
        return "bizOne";
    }
}
