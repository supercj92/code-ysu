package com.cfysu.spi.dubbo;

import com.alibaba.dubbo.common.URL;

/**
 * @Author canglong
 * @Date 2021/4/19
 */
public class HelloImpl2 implements Hello{

    private Test test;

    @Override
    public String sayHello(URL url) {
        return "HelloImpl2";
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Test getTest() {
        return test;
    }
}
