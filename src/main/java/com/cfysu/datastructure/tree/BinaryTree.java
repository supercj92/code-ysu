package com.cfysu.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * @Author canglong
 * @Date 2019/4/19
 */
public class BinaryTree {

    private BinaryTreeNode<Integer> root;
    private StringBuilder path = new StringBuilder();

    /**
     *              1
     *       2            3
     *     4   5        6   7
     *   8                    9
     *                          10
     */
    public BinaryTree(){
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> node5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> node6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> node7 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> node9 = new BinaryTreeNode<>(9);
        BinaryTreeNode<Integer> node10 = new BinaryTreeNode<>(10);

        node1.setLeftNode(node2);
        node1.setRightNode(node3);

        node2.setLeftNode(node4);
        node2.setRightNode(node5);

        node3.setLeftNode(node6);
        node3.setRightNode(node7);

        node4.setLeftNode(node8);

        node7.setRightNode(node9);

        node9.setLeftNode(node10);

        root = node1;
    }

    //深度优先遍历
    public void dfs(BinaryTreeNode root){
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BinaryTreeNode node = stack.pop();
            appendPath(node);
            if(node.getRightNode() != null){
                stack.push(node.getRightNode());
            }
            if(node.getLeftNode() != null){
                stack.push(node.getLeftNode());
            }
        }
    }

    //递归实现深度优先
    public void dfsRecursion(BinaryTreeNode root){
        if(root == null){
            return;
        }
        appendPath(root);
        dfsRecursion(root.getLeftNode());
        dfsRecursion(root.getRightNode());
    }

    //广度优先遍历
    public void bfs(BinaryTreeNode root){
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            BinaryTreeNode node = queue.poll();
            appendPath(node);
            if(node.getLeftNode() != null){
                queue.offer(node.getLeftNode());
            }
            if(node.getRightNode() != null){
                queue.offer(node.getRightNode());
            }
        }
    }

    public void preOrderTraversal(BinaryTreeNode node){
        if(node == null){
            return;
        }
        path.append(node.getData()).append(",");

        preOrderTraversal(node.getLeftNode());

        preOrderTraversal(node.getRightNode());

    }

    public void midOrderTraversal(BinaryTreeNode node){
        if(node == null){
            return;
        }

        midOrderTraversal(node.getLeftNode());

        path.append(node.getData()).append(",");

        midOrderTraversal(node.getRightNode());
    }

    public void postOrderTraversal(BinaryTreeNode node){
        if(node == null){
            return;
        }

        postOrderTraversal(node.getLeftNode());

        postOrderTraversal(node.getRightNode());

        path.append(node.getData()).append(",");
    }

    public void printTraversalPath(){
        System.out.println(path.toString());
        path = new StringBuilder();
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        ////前序遍历
        //binaryTree.preOrderTraversal(binaryTree.root);
        //binaryTree.printTraversalPath();
        //
        ////中序遍历
        //binaryTree.midOrderTraversal(binaryTree.root);
        //binaryTree.printTraversalPath();
        //
        ////后序遍历
        //binaryTree.postOrderTraversal(binaryTree.root);
        //binaryTree.printTraversalPath();

        binaryTree.dfs(binaryTree.root);
        binaryTree.printTraversalPath();

        binaryTree.dfsRecursion(binaryTree.root);
        binaryTree.printTraversalPath();

        binaryTree.bfs(binaryTree.root);
        binaryTree.printTraversalPath();
    }

    private void appendPath(BinaryTreeNode binaryTreeNode){
        path.append(binaryTreeNode.getData()).append(",");
    }
}
