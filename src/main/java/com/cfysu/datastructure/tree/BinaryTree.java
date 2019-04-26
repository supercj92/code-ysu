package com.cfysu.datastructure.tree;

/**
 * @Author canglong
 * @Date 2019/4/19
 */
public class BinaryTree {

    private TreeNode<Integer> root;
    private StringBuilder path = new StringBuilder();

    public BinaryTree(){
        TreeNode<Integer> node1 = new TreeNode<>(1);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node3 = new TreeNode<>(3);
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node5 = new TreeNode<>(5);
        TreeNode<Integer> node6 = new TreeNode<>(6);
        TreeNode<Integer> node7 = new TreeNode<>(7);
        TreeNode<Integer> node8 = new TreeNode<>(8);
        TreeNode<Integer> node9 = new TreeNode<>(9);
        TreeNode<Integer> node10 = new TreeNode<>(10);

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

    public void preOrderTraversal(TreeNode node){
        if(node == null){
            return;
        }
        path.append(node.getData()).append(",");

        preOrderTraversal(node.getLeftNode());

        preOrderTraversal(node.getRightNode());

    }

    public void midOrderTraversal(TreeNode node){
        if(node == null){
            return;
        }

        midOrderTraversal(node.getLeftNode());

        path.append(node.getData()).append(",");

        midOrderTraversal(node.getRightNode());
    }

    public void postOrderTraversal(TreeNode node){
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

        //前序遍历
        binaryTree.preOrderTraversal(binaryTree.root);
        binaryTree.printTraversalPath();

        //中序遍历
        binaryTree.midOrderTraversal(binaryTree.root);
        binaryTree.printTraversalPath();

        //后序遍历
        binaryTree.postOrderTraversal(binaryTree.root);
        binaryTree.printTraversalPath();
    }
}
