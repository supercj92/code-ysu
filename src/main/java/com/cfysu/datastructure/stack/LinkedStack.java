package com.cfysu.datastructure.stack;

import com.cfysu.datastructure.link.Link;

/**
 * @Author canglong
 * @Date 2021/3/11
 */
public class LinkedStack<T> implements Stack<T>{

    private Link<T> link;

    public LinkedStack(){
        link = new Link<>();
    }

    @Override
    public void push(T data) {
        link.insertFirst(data);
    }

    @Override
    public T pop() {
        return link.deleteFirst();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);

        stack.pop();
        stack.push(3);
        System.out.println();
    }
}
