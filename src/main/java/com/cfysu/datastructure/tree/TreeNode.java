package com.cfysu.datastructure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author canglong
 * @Date 2019/4/19
 */
@Data
public class TreeNode<T> {
    private T data;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode(T data){
        this.data = data;
    }
}
