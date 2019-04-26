package com.cfysu.datastructure.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author canglong
 * @Date 2019/4/25
 */
@Setter
@Getter
public class GraphNode<T> {
    private T data;
    private List<GraphNode<T>> neighbourNodeList;
    private boolean visited;

    public GraphNode(T data){
        this.data = data;
        neighbourNodeList = new ArrayList<>();
    }

    @Override
    public String toString() {
        if(data == null){
            return null;
        }
        return data.toString();
    }
}
