package com.cfysu.datastructure;

import org.apache.log4j.Logger;

/**
 * Created by cj on 17-6-18.
 */
public class Queue {

    private int maxSize;
    private int front;
    private int rear;
    private int[] queueArray;
    private int nElement;
    private static final Logger LOGGER = Logger.getLogger(Queue.class);

    public Queue(int size){
        this.maxSize = size;
        this.queueArray = new int[maxSize];
        this.front = 0;
        this.rear = -1;
        this.nElement = 0;
    }

    public void insert(int data){
        if (isFull()){
            LOGGER.warn("队列已满");
            return;
        }
        //先移动指针
        if(rear == (maxSize - 1)){
            rear = -1;
        }else {
            rear++;
        }
        queueArray[rear] = data;
        nElement++;
    }

    public int remove(){
        if (isEmpty()){
            LOGGER.warn("队列为空");
            return -1;
        }
        int temp = queueArray[front];
        nElement--;
        //移动指针
        if (front == maxSize){
            front = 0;
        }else {
            front++;
        }
        return temp;
    }

    public boolean isEmpty(){
        return (nElement == 0);
    }

    public boolean isFull(){
        return (nElement == maxSize);
    }

    public int size(){
        return nElement;
    }
}
