package com.cfysu.spring.context.qualifier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2021/5/10
 */
@Component
public class TestService {

    @Autowired
    //@Qualifier("test1")
    private Test test1;

    @Autowired
    //@Qualifier("test2")
    private Test test2;

    @Autowired
    public List<Test> testList;
}
