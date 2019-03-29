package com.cfysu.pattern.chain;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class ChainMain {

    public Node chain;

    public void invokeChain(){
        chain = new NodeA(new NodeB(new NodeC(null)));
        chain.process();
    }

    public static void main(String[] args) {
        ChainMain chainMain = new ChainMain();
        chainMain.invokeChain();
    }
}
