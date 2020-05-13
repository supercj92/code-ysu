package com.cfysu.pattern.chain2.demo;

import com.cfysu.pattern.chain2.AbstractNode;
import com.cfysu.pattern.chain2.Node;
import com.cfysu.pattern.chain2.Request;
import com.cfysu.pattern.chain2.Response;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class NodeB extends AbstractNode {

    public NodeB(Node next){
      super(next);
    }

    @Override
    public boolean support(Request request) {
        return true;
    }

    @Override
    public Response doProcess(Request request) {
        return new Response() {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

}
