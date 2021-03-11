package com.cfysu.datastructure.queue;

import com.cfysu.datastructure.link.Link;

/**
 * @Author canglong
 * @Date 2021/3/11
 */
public class LinkedQueue<T> implements Queue<T> {

    private Link<T> link;

    public LinkedQueue(){
        link = new Link<>();
    }

    @Override
    public void insert(T data) {
        link.insertLast(data);
    }

    @Override
    public T remove() {
        return link.deleteFirst();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedQueue<>();

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);

        queue.remove();
        queue.insert(4);

        System.out.println();
    }
}
