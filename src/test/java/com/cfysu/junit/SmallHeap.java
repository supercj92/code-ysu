/*package com.cfysu.junit;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.yhd.exam1.Combo.ResultItem;

*//**
 * 小根堆
 * @author weichao
 *
 *//*
public class SmallHeap {

    private int maxSize;
    private List<ResultItem> itemList;
    
    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<ResultItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ResultItem> itemList) {
        this.itemList = itemList;
    }

    public SmallHeap(int maxSize) {
        this.maxSize = maxSize;
        itemList = Lists.newArrayList();
    }
    
    *//**
     * 获取父节点的index
     * @param index
     * @return
     *//*
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }
    
    private int getBrotherIndex(int index) {
        if (index == 0) {
            return -1;
        }
        if (index % 2 == 0) {//为右节点，兄弟为左节点
            return index - 1;
        }
        if (index == itemList.size() - 1) {
            return -1;
        }
        return index + 1;
    }
    *//**
     * 交换两个节点在list中的位置
     * @param index1
     * @param index2
     *//*
    private void swapTwoNode(int index1, int index2) {
        ResultItem tmpItem = itemList.get(index1);
        itemList.set(index1, itemList.get(index2));
        itemList.set(index2, tmpItem);
    }
    
    *//**
     * 添加元素
     * @param item
     *//*
    public void addItem(ResultItem item) {
        if (itemList.size() == 0) {
            itemList.add(item);
            return;
        }
        if(itemList.size() < maxSize) {//直接先放进来，在从此节点向上冒泡比较
            itemList.add(item);
            int index = itemList.size() -1;
            swap2Top(index);
        }else if (item.isBigger(itemList.get(0))) {//需要进行交换
            itemList.set(0, null);
            itemList.set(0, item);
            //从根节点迭代向下进行比较
            swap2Bottom(0);
        }else {
            item = null;
        }
    }
    
    *//**
     * 向下比较
     * @param index
     *//*
    private void swap2Bottom(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = leftChildIndex + 1;
        if (leftChildIndex > itemList.size() - 1) {//无子节点
            return;
        }
        if (leftChildIndex == itemList.size() - 1) {//只有左子节点，无右子节点
            if (itemList.get(leftChildIndex).isBigger(itemList.get(index))) {
                return;
            }
        }else {
            int compareIndex = itemList.get(leftChildIndex).isBigger(itemList.get(rightChildIndex)) ? rightChildIndex: leftChildIndex;//选出较小的节点进行交换
            if (itemList.get(index).isBigger(itemList.get(compareIndex))) {
                swapTwoNode(index, compareIndex);
                swap2Bottom(compareIndex);
            }
        }
    }
    
    *//**
     * 向上比较并进行交换
     * @param index
     *//*
    private void swap2Top(int index) {//向上比较
        if (index == 0) {
            return;
        }
        int parentIndex = getParentIndex(index);
        if (itemList.get(index).isBigger(itemList.get(parentIndex))) {
            return;
        }
        int brotherIndex = getBrotherIndex(index);
        int swapIndex = index;
        if (brotherIndex > 0) {
            swapIndex = itemList.get(index).isBigger(itemList.get(brotherIndex)) ? brotherIndex: index;
        }
        //交换后继续向上比较
        swapTwoNode(swapIndex, parentIndex);
//        swap2Bottom(parentIndex);
        swap2Top(parentIndex);
        
    }
    
    public static void main(String[] args) {
        SmallHeap heap = new SmallHeap(5);
        ResultItem item1 = new ResultItem("1", "2", 1, 1.9);
        ResultItem item2 = new ResultItem("11", "2", 1, 1.97);
        ResultItem item3 = new ResultItem("111", "2", 1, 10.9);
        ResultItem item4 = new ResultItem("1111", "2", 1, 155.9);
        ResultItem item5 = new ResultItem("11111", "2", 1, 1.1);
        ResultItem item6 = new ResultItem("111111", "2", 1, 1.98);
        ResultItem item7 = new ResultItem("1111111", "2", 1, 19.9);
        ResultItem item8 = new ResultItem("11111111", "2", 1, 3);
        ResultItem item9 = new ResultItem("111111111", "2", 1, 1.94);
        ResultItem item10 = new ResultItem("1111111111", "2", 1, 0.2);
        heap.addItem(item1);
        heap.addItem(item2);
        heap.addItem(item3);
        heap.addItem(item4);
        heap.addItem(item5);
        heap.addItem(item6);
        heap.addItem(item7);
        heap.addItem(item8);
        heap.addItem(item9);
        heap.addItem(item10);
        System.out.println(JSON.toJSONString(heap.getItemList()));
    }
    
    
}
*/