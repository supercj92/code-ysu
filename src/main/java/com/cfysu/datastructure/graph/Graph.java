package com.cfysu.datastructure.graph;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author canglong
 * @Date 2019/4/25
 * 数据结构：图
 */
public class Graph<T> {

    private StringBuilder searchPath = new StringBuilder();

    public static void main(String[] args) {

        //深度优先
        Graph<Integer> graph = new Graph<>();

        GraphNode<Integer> startNode = graph.buildGraph();
        //使用递归实现深度优先遍历
        graph.deepFirstSearchByRecursive(startNode);
        graph.printAndResetPath();

        startNode = graph.buildGraph();
        //使用栈实现深度优先遍历
        graph.deepFirstSearchByStack(startNode);
        graph.printAndResetPath();

        startNode = graph.buildGraph();
        //使用队列实现广度优先
        graph.breadthFirstSearchByQueue(startNode);
        graph.printAndResetPath();
    }

    /**
     * 深度优先，递归方式实现
     */
    public void deepFirstSearchByRecursive(GraphNode<T> node){

        handleNode(node);
        List<GraphNode<T>> neighbourNodeList = node.getNeighbourNodeList();
        if(neighbourNodeList != null){
            for(GraphNode<T> nodeItem : neighbourNodeList){
                if(!nodeItem.isVisited()){
                    deepFirstSearchByRecursive(nodeItem);
                }
            }
        }

    }

    /**
     * 基于栈实现深度优先遍历
     */
    public void deepFirstSearchByStack(GraphNode<T> graphNode){
        Stack<GraphNode<T>> stack = new Stack<>();
        stack.push(graphNode);

        while (!stack.isEmpty()){
            GraphNode<T> node = stack.pop();
            if(node.getNeighbourNodeList() != null){
                for (GraphNode<T> neighbourNode : node.getNeighbourNodeList()){
                    if(!neighbourNode.isVisited()){
                        handleNode(neighbourNode);
                        stack.push(neighbourNode);
                    }
                }
            }
        }
    }

    /**
     * 依赖队列实现广度优先遍历
     */
    public void breadthFirstSearchByQueue(GraphNode<T> node){
        //队列
        Queue<GraphNode<T>> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(node);

        while (!nodeQueue.isEmpty()){
            GraphNode<T> nodeFromQueue = nodeQueue.poll();
            //如果在这里标记已处理的话，后面入队的元素可能会是已经被访问过得
            //handleNode(nodeFromQueue);

            List<GraphNode<T>> neighbourNodeList = nodeFromQueue.getNeighbourNodeList();
            if(neighbourNodeList != null){
                for(GraphNode<T> neighbourNode : neighbourNodeList){
                    if(!neighbourNode.isVisited()){
                        //处理之后再入队，可以保证每个入队的元素都是已经被处理过的
                        handleNode(neighbourNode);
                        nodeQueue.offer(neighbourNode);
                    }
                }
            }
        }
    }

    private void handleNode(GraphNode<T> graphNode){
        searchPath.append(graphNode.getData()).append(",");
        graphNode.setVisited(true);
    }

    /**
     * 画不出来图的结构了...
     *                    1
     *                /       \
     *               2         3
     *           /  /   \  /    \   \
     *         4 - 5  -   6       7   8
     *                   /  \
     *                  9  - 10
     */
    private GraphNode<Integer> buildGraph(){
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

    private void printAndResetPath(){
        System.out.println(searchPath.toString());
        resetPath();
    }

    private void resetPath(){
        searchPath = new StringBuilder();
    }
}
