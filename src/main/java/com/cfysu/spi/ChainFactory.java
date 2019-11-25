package com.cfysu.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @Author canglong
 * @Date 2019/11/6
 */
public class ChainFactory {

    /**
     * 构造双向链表
     */
    public static AbstractNode buildDoubleDirectionChain(ServiceLoader<AbstractNode> providers){
        //todo 缓存
        AbstractNode head = new Node1();
        AbstractNode tail = new Node1();
        head.next = tail;
        tail.pre = head;

        List<AbstractNode> nodeList = sortNode(providers);
        //Comparator.comparingInt(Order::getOrder);

        //在双向列表中插入两个节点
        for(AbstractNode currentProvider : nodeList){
            AbstractNode last2 = tail.pre;
            currentProvider.next = tail;
            currentProvider.pre = last2;
            last2.next = currentProvider;
            tail.pre = currentProvider;
        }
        return head;
    }

    /**
     * 单向列表
     */
    public static AbstractNode buildSingleDirectionChain(ServiceLoader<AbstractNode> providers){
        AbstractNode current = new Node1();
        AbstractNode head = current;
        List<AbstractNode> nodeList = sortNode(providers);
        for(AbstractNode nameProvider : nodeList){
            current.next = nameProvider;
            current = nameProvider;
        }
        return head;
    }

    private static List<AbstractNode> sortNode(ServiceLoader<AbstractNode> providers){
        List<AbstractNode> nodeList = new ArrayList<>();
        for(AbstractNode currentProvider : providers){
            nodeList.add(currentProvider);
        }
        //sort node
        nodeList.sort((o1, o2) -> Integer.compare(o1.getOrder(), o2.getOrder()));
        return nodeList;
    }
}
