package com.cfysu.datastructure.graph;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * @Author canglong
 * @Date 2019/4/25
 */
public class GraphTraversal {

    StringBuilder path = new StringBuilder();

    public static void main(String[] args) {

        //深度优先
        GraphTraversal graphTraversal = new GraphTraversal();
        GraphNode<Integer> startNode = graphTraversal.buildGraph();
        graphTraversal.deepFirstSearch(startNode);
        System.out.println(graphTraversal.path.toString());
        graphTraversal.resetPath();

        //广度优先
        //graphTraversal.breadthFiserSearch(startNode);

    }

    /**
     * 深度优先，递归方式实现
     */
    public void deepFirstSearch(GraphNode<Integer> node){

        path.append(node.getData()).append(",");
        node.setVisited(true);
        List<GraphNode<Integer>> neighbourNodeList = node.getNeighbourNodeList();
        if(neighbourNodeList == null || neighbourNodeList.isEmpty()){
            return;
        }

        for(GraphNode<Integer> nodeItem : neighbourNodeList){
            if(nodeItem.isVisited()){
                continue;
            }
            deepFirstSearch(nodeItem);
        }

    }

    /**
     * 广度优先
     */
    public void breadthFiserSearch(GraphNode<Integer> node){
        //双端队列
        Queue<GraphNode<Integer>> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(node);

        while (!nodeQueue.isEmpty()){
            GraphNode<Integer> nodeFromQueue = nodeQueue.poll();

            List<GraphNode<Integer>> neighbourNodeList = nodeFromQueue.getNeighbourNodeList();
            if(neighbourNodeList == null || neighbourNodeList.isEmpty()){
                continue;
            }
            for(GraphNode<Integer> nodeItem : neighbourNodeList){
                if(nodeItem.isVisited()){
                    continue;
                }

                path.append(nodeItem.getData()).append(",");
                nodeItem.setVisited(true);
                nodeQueue.offer(nodeItem);
            }
        }

        System.out.println(path.toString());
    }

    public GraphNode<Integer> buildGraph(){
        GraphNode<Integer> node1 = new GraphNode<>(1);
        GraphNode<Integer> node2 = new GraphNode<>(2);
        GraphNode<Integer> node3 = new GraphNode<>(3);
        GraphNode<Integer> node4 = new GraphNode<>(4);
        GraphNode<Integer> node5 = new GraphNode<>(5);
        GraphNode<Integer> node6 = new GraphNode<>(6);
        GraphNode<Integer> node7 = new GraphNode<>(7);
        GraphNode<Integer> node8 = new GraphNode<>(8);
        GraphNode<Integer> node9 = new GraphNode<>(9);
        GraphNode<Integer> node10 = new GraphNode<>(10);

        node1.getNeighbourNodeList().add(node2);
        node1.getNeighbourNodeList().add(node3);

        node2.getNeighbourNodeList().add(node4);
        node2.getNeighbourNodeList().add(node5);
        node2.getNeighbourNodeList().add(node6);

        node3.getNeighbourNodeList().add(node1);
        node3.getNeighbourNodeList().add(node6);
        node3.getNeighbourNodeList().add(node7);
        node3.getNeighbourNodeList().add(node8);

        node4.getNeighbourNodeList().add(node2);
        node4.getNeighbourNodeList().add(node5);

        node5.getNeighbourNodeList().add(node2);
        node5.getNeighbourNodeList().add(node4);
        node5.getNeighbourNodeList().add(node6);

        node6.getNeighbourNodeList().add(node2);
        node6.getNeighbourNodeList().add(node5);
        node6.getNeighbourNodeList().add(node3);
        node6.getNeighbourNodeList().add(node8);
        node6.getNeighbourNodeList().add(node9);
        node6.getNeighbourNodeList().add(node10);

        node7.getNeighbourNodeList().add(node3);

        node8.getNeighbourNodeList().add(node3);
        node8.getNeighbourNodeList().add(node6);
        node8.getNeighbourNodeList().add(node9);

        node9.getNeighbourNodeList().add(node6);
        node9.getNeighbourNodeList().add(node8);
        node9.getNeighbourNodeList().add(node10);

        node10.getNeighbourNodeList().add(node6);
        node10.getNeighbourNodeList().add(node9);

        return node1;
    }

    public void resetPath(){
        path = new StringBuilder();
    }
}
