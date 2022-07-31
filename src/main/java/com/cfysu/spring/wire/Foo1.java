package com.cfysu.spring.wire;

import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2022/7/14
 */
@Component
public class Foo1 implements Foo<String, String>{
    @Override
    public String bar(String s) {
        return null;
    }
}
