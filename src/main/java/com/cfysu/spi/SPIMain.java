package com.cfysu.spi;

import java.util.ServiceLoader;

/**
 * @Author canglong
 * @Date 2019/7/15
 * 基于spi，构造责任链
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<AbstractNode> providers = ServiceLoader.load(AbstractNode.class);
        Node head = ChainFactory.buildSingleDirectionChain(providers);
        Response response = head.invoke(new Request());
    }
}
