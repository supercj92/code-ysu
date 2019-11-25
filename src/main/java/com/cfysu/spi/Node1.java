package com.cfysu.spi;

/**
 * @Author canglong
 * @Date 2019/7/15
 */
public class Node1 extends AbstractNode {
    @Override
    public Response printName(Request request) {
        System.out.println("Node1");
        return null;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
