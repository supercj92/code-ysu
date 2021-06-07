package com.cfysu.spi.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author canglong
 * @Date 2021/4/19
 */
@SPI
public interface Hello {
    @Adaptive("name")
    String sayHello(URL url);
}
