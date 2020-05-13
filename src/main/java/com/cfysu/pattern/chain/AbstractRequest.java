package com.cfysu.pattern.chain;

/**
 * @Author canglong
 * @Date 2020/5/13
 */
public abstract class AbstractRequest<T> implements Request<T>{

    private T t;
    private String handledNodeName;

    public T getData() {
        return t;
    }

    @Override
    public void markProcessed(String nodeName) {
       this.handledNodeName = nodeName;
    }
}
