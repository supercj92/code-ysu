package com.cfysu.datastructure.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author canglong
 * @Date 2021/3/11
 */
public class MultiNodeTree<T> {

    private TreeNode<T> root;

    private static StringBuilder searchPath = new StringBuilder();

    public static void main(String[] args) {
        MultiNodeTree<Integer> tree = new MultiNodeTree<>();
        TreeNode<Integer> root = tree.build();

        tree.deepFirstSearchByRecursive(root.getSubNodes());
        System.out.println(searchPath.toString());
    }

    /**
     * 递归实现深度优先遍历
     */
    public void deepFirstSearchByRecursive(List<TreeNode<T>> subNodes){
        for (TreeNode<T> node : subNodes){
            handleNode(node);
            if(node.getSubNodes() != null){
                deepFirstSearchByRecursive(node.getSubNodes());
            }
        }
    }

    /**
     * 依赖栈实现深度优先遍历
     */
    public void deepFirstSearchByStack(TreeNode<T> root){
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode<T> node = stack.pop();
            handleNode(node);
            if(node.getSubNodes() != null){
                for (TreeNode<T> subNode : node.getSubNodes()){
                    stack.push(subNode);
                }
            }
        }
    }

    /**
     * 依赖队列实现广度优先遍历
     */
    public void breadthFirstSearchByQueue(TreeNode<T> root){
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode<T> node = queue.poll();
            handleNode(node);

            if(node.getSubNodes() != null){
                for (TreeNode<T> subNode : node.getSubNodes()){
                    queue.offer(subNode);
                }
            }
        }
    }


    private void handleNode(TreeNode<T> node){
        searchPath.append(node.getData()).append(",");
    }

    /**
     *                       1
     *        21            22             23
     *    31 32 33    34 35 36 37 38
     */
    private TreeNode<Integer> build(){
        //第一层
        TreeNode<Integer> root = new TreeNode<>(1);

        //第二层
        TreeNode<Integer> node21 = new TreeNode<>(21);
        TreeNode<Integer> node22 = new TreeNode<>(22);
        TreeNode<Integer> node23 = new TreeNode<>(23);
        List<TreeNode<Integer>> subNodes2 = new ArrayList<>();
        subNodes2.add(node21);
        subNodes2.add(node22);
        subNodes2.add(node23);

        //第三层
        TreeNode<Integer> node31 = new TreeNode<>(31);
        TreeNode<Integer> node32 = new TreeNode<>(32);
        TreeNode<Integer> node33 = new TreeNode<>(33);
        List<TreeNode<Integer>> subNodes31 = new ArrayList<>();
        subNodes31.add(node31);
        subNodes31.add(node32);
        subNodes31.add(node33);

        TreeNode<Integer> node34 = new TreeNode<>(34);
        TreeNode<Integer> node35 = new TreeNode<>(35);
        TreeNode<Integer> node36 = new TreeNode<>(36);
        TreeNode<Integer> node37 = new TreeNode<>(37);
        TreeNode<Integer> node38 = new TreeNode<>(38);
        List<TreeNode<Integer>> subNodes32 = new ArrayList<>();
        subNodes32.add(node34);
        subNodes32.add(node35);
        subNodes32.add(node36);
        subNodes32.add(node37);
        subNodes32.add(node38);

        root.setSubNodes(subNodes2);

        node21.setSubNodes(subNodes31);

        node22.setSubNodes(subNodes32);

        return root;
    }
}
