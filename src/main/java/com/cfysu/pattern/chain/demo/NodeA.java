package com.cfysu.pattern.chain.demo;

import com.cfysu.pattern.chain.Node;
import com.cfysu.pattern.chain.AbstractNode;
import com.cfysu.pattern.chain.Request;
import com.cfysu.pattern.chain.Response;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class NodeA extends AbstractNode<Question, Answer> {

    public NodeA(Node<Question, Answer> next){
        super(next);
    }

    @Override
    public boolean support(Request<Question> request) {
        return false;
    }

    @Override
    public Response<Answer> doProcess(Request<Question> request) {
        return null;
    }

}
