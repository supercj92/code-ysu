package com.cfysu.pattern.chain2.demo;

import com.cfysu.pattern.chain2.AbstractNode;
import com.cfysu.pattern.chain2.Node;
import com.cfysu.pattern.chain2.Request;
import com.cfysu.pattern.chain2.Response;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class NodeA extends AbstractNode {

    public NodeA(Node next){
        super(next);
    }

    @Override
    public boolean support(Request request) {
        return false;
    }

    @Override
    public Response doProcess(Request request) {
        return null;
    }

}
