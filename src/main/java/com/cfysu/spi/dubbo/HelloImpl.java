package com.cfysu.spi.dubbo;

import com.alibaba.dubbo.common.URL;

/**
 * @Author canglong
 * @Date 2021/4/19
 */
public class HelloImpl implements Hello{

    @Override
    public String sayHello(URL url) {
        return "HelloImpl";
    }
}
