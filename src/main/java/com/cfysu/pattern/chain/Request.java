package com.cfysu.pattern.chain;

/**
 * @Author canglong
 * @Date 2020/5/13
 */
public interface Request<I> {
    void markProcessed(String nodeName);
}
