package com.cfysu.datastructure.link;

import lombok.Data;

/**
 * Created by cj on 17-6-17.
 */
@Data
public class Node<T> {

    private T data;
    private Node<T> next;

    private Node<T> pre;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public Node(T data, Node<T> pre, Node<T> next){
        this.data = data;
        this.pre = pre;
        this.next = next;
    }

    @Override
    public String toString(){
        return data.toString();
    }
}
