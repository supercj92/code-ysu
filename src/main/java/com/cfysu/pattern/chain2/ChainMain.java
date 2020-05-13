package com.cfysu.pattern.chain2;

import com.cfysu.pattern.chain2.demo.Answer;
import com.cfysu.pattern.chain2.demo.DefaultRequest;
import com.cfysu.pattern.chain2.demo.NodeA;
import com.cfysu.pattern.chain2.demo.NodeB;
import com.cfysu.pattern.chain2.demo.NodeC;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class ChainMain {

    public Node chain;

    public void invokeChain(){
        chain = new NodeA(new NodeB(new NodeC(null)));
        Request request = new DefaultRequest();
        Response process = chain.process(request);
        System.out.println(process);
    }

    public static void main(String[] args) {
        ChainMain chainMain = new ChainMain();
        chainMain.invokeChain();
    }
}
