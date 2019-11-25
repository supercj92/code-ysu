package com.cfysu.spi;

/**
 * @Author canglong
 * @Date 2019/11/6
 */
public interface Node {
    Response invoke(Request request);
}
