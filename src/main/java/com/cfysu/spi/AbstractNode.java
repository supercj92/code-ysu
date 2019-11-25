package com.cfysu.spi;

/**
 * @Author canglong
 * @Date 2019/7/15
 */
public abstract class AbstractNode implements Node, Printable, Order{
    protected AbstractNode next;
    protected AbstractNode pre;

    @Override
    public Response invoke(Request request){
        printName(request);
        if(next != null){
            return next.invoke(request);
        }
        return null;
    }
}
