package com.cfysu.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author canglong
 * @Date 2020/5/15
 */
public class TreeTraversal {

    private Stack<Long> path = new Stack<>();

    public static void main(String[] args) {
        TreeTraversal treeTraversal = new TreeTraversal();
        Node root = treeTraversal.buildTree();

        treeTraversal.traversal(root);
        System.out.println(JSONObject.toJSONString(treeTraversal.path));

        while (!treeTraversal.path.isEmpty()){
            Long pop = treeTraversal.path.pop();
            System.out.println(pop);
        }
    }

    public void traversal(Node node){
        //处理节点
        handleNode(node);

        //处理子节点
        List<Node> subNodeList = node.getSubNode();
        if(subNodeList != null && !subNodeList.isEmpty()){
            for (Node subNode : subNodeList){
                traversal(subNode);
            }
        }
    }

    public void handleNode(Node node){
        path.push(node.getNodeId());
    }

    public Node buildTree(){
        Node root = new Node();
        root.setNodeId(1L);
        List<Node> NodeList1 = new ArrayList<>();
        NodeList1.add(root);


        Node Node2 = new Node();
        Node2.setNodeId(2L);
        Node node20 = new Node();
        node20.setNodeId(20L);

        List<Node> NodeList2 = new ArrayList<>();
        NodeList2.add(Node2);
        NodeList2.add(node20);
        root.setSubNode(NodeList2);


        Node Node3 = new Node();
        Node3.setNodeId(3L);
        List<Node> NodeList3 = new ArrayList<>();
        NodeList3.add(Node3);
        Node2.setSubNode(NodeList3);
        return root;
    }

    public class Node{
        @Getter
        @Setter
       private Long nodeId;

        @Getter
        @Setter
       private List<Node> subNode;
    }
}
