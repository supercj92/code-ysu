package com.cfysu.datastructure.stack;

/**
 * Created by canglong on 2017/6/8.
 */
public class ArrayStack<T> implements Stack<T>{
    private Object[] stackArray;
    private int pointer;
    private int maxSize;

    public ArrayStack(int maxSize){
        this.stackArray = new Object[maxSize];
        this.maxSize = maxSize;
        this.pointer = -1;
    }

    @Override
    public void push(T element){
        if(isFull()){
            System.out.println("栈已满");
           return;
        }
        pointer++;
        stackArray[pointer] = element;
    }

    @Override
    public T pop(){
        T element = null;
        if(!isEmpty()) {
            element = (T)stackArray[pointer];
            pointer--;
        }
        return element;
    }

    private boolean isEmpty(){
        if(pointer == -1){
            return true;
        }
        return false;
    }

    private boolean isFull(){
        if((pointer + 1) == maxSize){
            return true;
        }
        return false;
    }
}
