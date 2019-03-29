package com.cfysu.pattern.chain;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class NodeB extends AbstractNode{

    public NodeB(Node next){
        this.next = next;
    }
    @Override
    public void process() {
        System.out.println("b");
        super.process();
    }
}
