package com.cfysu.datastructure;

/**
 * Created by longcangjian on 2017/6/8.
 */
public class Stack {
    private char[] stackArray;
    private int pointer;
    private int maxSize;

    public Stack(int maxSize){
        this.stackArray = new char[maxSize];
        this.maxSize = maxSize;
        this.pointer = -1;
    }

    public void push(char element){
        if(isFull()){
            System.out.println("栈已满");
           return;
        }
        pointer++;
        stackArray[pointer] = element;
    }

    public char pop(){
        char element = '0';
        if(!isEmpty()) {
            element = stackArray[pointer];
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
