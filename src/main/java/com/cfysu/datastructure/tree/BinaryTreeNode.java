package com.cfysu.datastructure.tree;

import lombok.Data;

/**
 * @Author canglong
 * @Date 2019/4/19
 */
@Data
public class BinaryTreeNode<T> {
    private T data;
    private BinaryTreeNode<T> leftNode;
    private BinaryTreeNode<T> rightNode;

    public BinaryTreeNode(T data){
        this.data = data;
    }
}
