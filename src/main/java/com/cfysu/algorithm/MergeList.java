package com.cfysu.algorithm;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/10/27
 */
public class MergeList {

    @Test
    public void testMergeTwoLists(){
        ListNode list1 = new ListNode(1, new ListNode(3, new ListNode(5,null)));
        ListNode list2 = new ListNode(2, new ListNode(4, new ListNode(6,null)));
        ListNode listNode = mergeTwoLists(list1, list2);
        System.out.println(JSON.toJSONString(listNode));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode current = list1;
        while (current != null) {
            list2 = insertList(current, list2);
            current = current.next;
        }
        return list2;
    }

    private ListNode insertList(ListNode node, ListNode list) {
        ListNode toInsert = new ListNode(node.val, null);
        ListNode current = list;
        ListNode preNode = null;
        while (current != null) {
            //插入节点
            ListNode next = current.next;
            //大于则插入在当前节点后面
            if (toInsert.val > current.val && (next == null || toInsert.val <= next.val)) {
                current.next = toInsert;
                toInsert.next = next;
                return list;
                //break;
            }
            //小于则插入当前节点前面
            if (preNode != null && toInsert.val > preNode.val && toInsert.val <= current.val){
                preNode.next = toInsert;
                toInsert.next = current;
                //break;
                return list;
            }
            if (preNode == null && toInsert.val <= current.val){
                toInsert.next = current;
                return toInsert;
                //break;
            }
            preNode = current;
            current = current.next;
        }
        return list;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {this.val = val;}

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
