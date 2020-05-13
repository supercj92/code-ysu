package com.cfysu.pattern.chain;

import com.cfysu.pattern.chain.demo.Answer;
import com.cfysu.pattern.chain.demo.DefaultRequest;
import com.cfysu.pattern.chain.demo.NodeA;
import com.cfysu.pattern.chain.demo.NodeB;
import com.cfysu.pattern.chain.demo.NodeC;
import com.cfysu.pattern.chain.demo.Question;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class ChainMain {

    public Node<Question, Answer> chain;

    public void invokeChain(){
        chain = new NodeA(new NodeB(new NodeC(null)));
        Request<Question> request = new DefaultRequest<>();
        Response<Answer> process = chain.process(request);
        System.out.println(process);
    }

    public static void main(String[] args) {
        ChainMain chainMain = new ChainMain();
        chainMain.invokeChain();
    }
}
