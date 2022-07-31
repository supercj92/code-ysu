package com.cfysu.spring.wire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2022/7/14
 */
@Component
public class DemoService {

    @Autowired
    private Foo<String, String> foo;

    @Autowired
    private Foo<String, Long> foo2;

    @Autowired
    private Foo<Long, Long> foo3;

}
