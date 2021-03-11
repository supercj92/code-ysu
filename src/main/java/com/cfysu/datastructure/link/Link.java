package com.cfysu.datastructure.link;


import org.apache.log4j.Logger;

/**
 * Created by cj on 17-6-17.
 */
public class Link<T> {
    private static final Logger logger = Logger.getLogger(Link.class);

    private Node<T> first;

    public Link(){
        this.first = null;
    }

    /**
     * 从头部插入节点
     * @param data
     */
    public void insertFirst(T data){
        Node<T> node = new Node<>(data);
        node.setNext(first);
        first = node;
    }

    /**
     * 删除头节点
     */
    public T deleteFirst(){
        if(isEmpty()){
            logger.info("链表为空");
            return null;
        }
        Node<T> temp = first;
        first = temp.getNext();
        //置空被删除节点的引用
        temp.setNext(null);
        return temp.getData();
    }

    /**
     * 在链表尾部插入
     */
    public void insertLast(T data){
        //当前头结点为空时，插入头结点
        if(first == null){
            insertFirst(data);
            return;
        }
        Node<T> currentNode = first;
        while (currentNode != null){
            if(currentNode.getNext() == null){
                Node<T> newNode = new Node<>(data);
                currentNode.setNext(newNode);
                break;
            }
            currentNode = currentNode.getNext();
        }
    }

    /**
     * 链表尾号删除
     */
    public T deleteLast(){
        Node<T> currentNode = first;
        while (currentNode != null){

            Node<T> nodeAfterCurrent = currentNode.getNext();
            //如果nodeAfterCurrent没有后续节点，则nodeAfterCurrent为链表的尾部
            if(nodeAfterCurrent.getNext() == null){
                currentNode.setNext(null);
                return nodeAfterCurrent.getData();
            }
            //移动指针
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void displayList(){
        StringBuilder stringBuilder = new StringBuilder();
        Node<T> currentNode = first;
        while (currentNode != null){
            stringBuilder.append(currentNode.toString()).append(",");
            currentNode = currentNode.getNext();
        }
        System.out.println(stringBuilder.toString());
    }
}
