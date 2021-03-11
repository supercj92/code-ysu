package com.cfysu.datastructure.link;

import lombok.Data;

/**
 * Created by cj on 17-6-17.
 */
@Data
public class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString(){
        return data.toString();
    }
}
