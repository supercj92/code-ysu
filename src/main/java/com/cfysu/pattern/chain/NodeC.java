package com.cfysu.pattern.chain;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class NodeC extends AbstractNode{

    public NodeC(Node next){
        this.next = next;
    }

    @Override
    public void process() {
        System.out.println("c");
        super.process();
    }
}
