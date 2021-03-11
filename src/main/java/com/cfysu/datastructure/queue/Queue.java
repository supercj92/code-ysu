package com.cfysu.datastructure.queue;

/**
 * @Author canglong
 * @Date 2021/3/11
 */
public interface Queue<T> {

    void insert(T data);

    T remove();
}
