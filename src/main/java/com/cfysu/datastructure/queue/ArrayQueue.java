package com.cfysu.datastructure.queue;

import org.apache.log4j.Logger;

/**
 * Created by cj on 17-6-18.
 */
public class ArrayQueue<T> {

    private static final Logger LOGGER = Logger.getLogger(ArrayQueue.class);
    /**
     * 队列的最大容量
     */
    private int maxSize;
    /**
     * 队列当前容量
     */
    private int currentSize;
    /**
     * 头指针
     */
    private int front;
    /**
     * 尾指针
     */
    private int rear;

    /**
     * 队列容器
     */
    private Object[] queueArray;

    public ArrayQueue(int size){
        this.maxSize = size;
        this.queueArray = new Object[maxSize];
        this.front = 0;
        this.rear = -1;
        this.currentSize = 0;
    }

    public void insert(int data){
        if (isFull()){
            LOGGER.warn("队列已满");
            return;
        }
        //尾指针已经是数组的最后一个元素了，则尾指针移到数组最前
        if(rear == (maxSize - 1)){
            rear = 0;
        }else {
            rear++;
        }
        queueArray[rear] = data;
        currentSize++;
    }

    public T remove(){
        if (isEmpty()){
            LOGGER.warn("队列为空");
            return null;
        }
        T temp = (T)queueArray[front];
        queueArray[front] = null;
        currentSize--;
        //移动指针
        if (front == maxSize){
            front = 0;
        }else {
            front++;
        }
        return temp;
    }

    public boolean isEmpty(){
        return (currentSize == 0);
    }

    public boolean isFull(){
        return (currentSize == maxSize);
    }

    public int size(){
        return currentSize;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);

        System.out.println(queue.remove());
        queue.insert(5);
        System.out.println(queue.remove());
    }
}
