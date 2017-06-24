package com.cfysu.datastructure.link;


import org.apache.log4j.Logger;

/**
 * Created by cj on 17-6-17.
 */
public class Link {
    private Node first;
    private static final Logger logger = Logger.getLogger(Link.class);
    public Link(){
        this.first = null;
    }

    /**
     * 从头部插入节点
     * @param data
     */
    public void insertFirst(int data){
        Node node = new Node(data);
        node.setNext(first);
        first = node;
    }

    /**
     * 删除头节点
     */
    public int deleteFirst(){
        if(isEmpty()){
            logger.info("链表为空");
            return -1;
        }
        Node temp = first;
        first = temp.getNext();
        return temp.getiData();
    }

    /**
     * 在链表尾部插入
     */
    public void insertLast(int data){
        Node currentNode = first;
        while (currentNode != null){
            if(currentNode.getNext() == null){
                Node newNode = new Node(data);
                currentNode.setNext(newNode);
                break;
            }
            currentNode = currentNode.getNext();
        }
    }

    public void deleteLast(){
        Node currentNode = first;
        while (currentNode != null){
            //倒数第二个节点
            if(currentNode.getNext().getNext() == null){
                currentNode.setNext(null);
                break;
            }
            //移动指针
            currentNode = currentNode.getNext();
        }
    }

    public boolean isEmpty(){
        return first == null ? true : false;
    }

    public void displayList(){
        Node currentNode = first;
        while (currentNode != null){
            currentNode.dispalyNode();
            currentNode = currentNode.getNext();
        }
        System.out.println();
    }
}
