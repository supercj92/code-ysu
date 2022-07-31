package com.cfysu.spring.wire;

import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2022/7/14
 */
@Component
public class Foo2 implements Foo<String, Long>{

    @Override
    public Long bar(String s) {
        return null;
    }
}
