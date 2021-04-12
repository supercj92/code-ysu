package com.cfysu.algorithm;

import com.cfysu.spi.Node;

/**
 * @Author canglong
 * @Date 2021/3/24
 * 原链表：1,2,3,4,5,6,7....a[n-2],a[n-1],a[n]
 * 排序后：1,a[n],2,[n-1],3,[n-2]...
 */
public class SortList {


    public void sortList(Node root){
        int linkSize = countLink(root);
        int countNum = 0;
        while (countNum < (linkSize / 2)){
            countNum++;
            Node tail = findTail(root);
            insertNode(root, tail, 2 * countNum);
        }
    }

    private Node findTail(Node root){
        Node current = root;
        while (current.next.next != null){
            current = current.next;
        }
        Node tail = current.next;
        current.next = null;
        return tail;
    }

    private void insertNode(Node root, Node node, int position){
        int counter = 0;
        Node before = null;
        Node current = root;
        while (current.next != null){
            counter++;
            if(counter == position){
                break;
            }
            current = current.next;
        }
        before = current;
        //插入节点
        node.next = before.next;
        before.next = node;
    }

    private int countLink(Node root){
        Node current = root;
        int counter = 0;
        while (current.next != null){
            current = current.next;
            counter++;
        }
        return counter;
    }

    class Node{
        public Node next;
    }
}
