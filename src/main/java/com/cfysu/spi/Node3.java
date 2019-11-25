package com.cfysu.spi;

/**
 * @Author canglong
 * @Date 2019/11/6
 */
public class Node3 extends AbstractNode {
    @Override
    public Response printName(Request request) {
        System.out.println("Node3");
        return null;
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
