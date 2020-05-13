package com.cfysu.pattern.chain.demo;

import com.cfysu.pattern.chain.AbstractNode;
import com.cfysu.pattern.chain.Node;
import com.cfysu.pattern.chain.Request;
import com.cfysu.pattern.chain.Response;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class NodeB extends AbstractNode<Question, Answer> {

    public NodeB(Node<Question, Answer> next){
      super(next);
    }

    @Override
    public boolean support(Request<Question> request) {
        return true;
    }

    @Override
    public Response<Answer> doProcess(Request<Question> request) {
        return new Response<Answer>() {
            @Override
            public Answer getData() {
                return new Answer();
            }
        };
    }

}
