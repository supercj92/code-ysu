package com.cfysu.pattern.chain;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class NodeA extends AbstractNode{

    public NodeA(Node next){
        this.next = next;
    }

    @Override
    public void process() {
        System.out.println("a");
        super.process();
    }
}
