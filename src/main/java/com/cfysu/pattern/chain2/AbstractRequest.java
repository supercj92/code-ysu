package com.cfysu.pattern.chain2;

/**
 * @Author canglong
 * @Date 2020/5/13
 */
public abstract class AbstractRequest implements Request {

    private String handledNodeName;

    @Override
    public void markProcessed(String nodeName) {
       this.handledNodeName = nodeName;
    }
}
