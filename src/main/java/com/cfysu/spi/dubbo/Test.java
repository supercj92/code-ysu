package com.cfysu.spi.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author canglong
 * @Date 2021/5/10
 */
@SPI
public interface Test {
    @Adaptive
    void test(URL url);
}
