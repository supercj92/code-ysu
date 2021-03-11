package com.cfysu.datastructure.tree;

import java.util.List;

import lombok.Data;

/**
 * @Author canglong
 * @Date 2021/3/11
 */
@Data
public class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> subNodes;

    public TreeNode(T data){
        this.data = data;
    }
}
