package com.cfysu.pattern.chain;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public abstract class AbstractNode implements Node{

    protected Node next;

    @Override
    public void process() {
        if(next != null){
            next.process();
        }
    }
}
