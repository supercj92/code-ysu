package com.cfysu.pattern.chain2;

/**
 * @Author canglong
 * @Date 2020/5/13
 */
public interface Node {
    Response process(Request request);
}
