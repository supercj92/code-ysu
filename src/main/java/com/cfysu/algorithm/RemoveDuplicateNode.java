package com.cfysu.algorithm;

import com.cfysu.datastructure.link.Node;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/3/3
 * 链表去重
 * 1,2,2,4 -> 1,2,4
 */
public class RemoveDuplicateNode {

    @Test
    public void testRemove() {
        Node<Integer> head = buildLink();
        removeDuplicateNode(head);
    }

    private Node<Integer> buildLink() {
        Node<Integer> node1 = new Node<>(1, null, null);
        Node<Integer> node2 = new Node<>(2, node1, null);
        Node<Integer> node3 = new Node<>(2, node2, null);
        Node<Integer> node4 = new Node<>(44, node3, null);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        return node1;
    }

    private void removeDuplicateNode(Node<Integer> link) {
        Node<Integer> currentNode = link;
        while (currentNode.getNext() != null) {
            if (count(currentNode, link) > 1) {
                remove(currentNode, link);
            }
            currentNode = currentNode.getNext();
        }
    }

    private int count(Node<Integer> nodeToSearch, Node<Integer> link) {
        Node<Integer> currentNode = link;
        int count = 0;
        while (currentNode != null) {
            if (currentNode.getData().equals(nodeToSearch.getData())) {
                count++;
            }
            currentNode = currentNode.getNext();
        }
        return count;
    }

    private Node<Integer> remove(Node<Integer> nodeToDelete, Node<Integer> link) {
        Node<Integer> currentNode = link;
        while (currentNode != null) {
            if (currentNode.getData().equals(nodeToDelete.getData())) {
                //del
                Node<Integer> nodeAfterCurrent = currentNode.getNext();
                Node<Integer> nodeBeforeCurrent = currentNode.getPre();
                if (nodeAfterCurrent != null) {
                    nodeAfterCurrent.setPre(nodeBeforeCurrent);
                }
                if (nodeBeforeCurrent != null) {
                    nodeBeforeCurrent.setNext(nodeAfterCurrent);
                }
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

}
