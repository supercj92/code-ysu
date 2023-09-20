package com.cfysu.lab.leetcode;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/9/19
 */
public class Solution2 {

    //l1  1 -> 2 -> 3
    //l2  3 -> 4 -> 5
    //output 4 -> 6 -> 8
    @Test
    public void testAddTwoNumbers() {
        //ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        //ListNode head2 = new ListNode(3, new ListNode(4, new ListNode(5)));
        //ListNode listNode = addTwoNumbers(head1, head2);
        //System.out.println();
        System.out.println(Integer.MAX_VALUE);
        System.out.println("9999999991");
        System.out.println(Integer.valueOf("9999999991"));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //转数字
        Integer num1 = toInteger(l1);
        Integer num2 = toInteger(l2);

        //求和
        Integer sum = num1 + num2;

        //数字转链表
        ListNode head = toLink(sum);

        //反转链表
        return revert(head);
    }

    private ListNode toLink(Integer num) {
        String numString = String.valueOf(num);
        char[] chars = numString.toCharArray();
        ListNode head = null;
        ListNode current = null;
        for (int i = 0; i < chars.length; i++) {
            int parseInt = Integer.parseInt(String.valueOf(chars[i]));
            if (i == 0) {
                head = new ListNode(parseInt);
                current = head;
            } else {
                current.next = new ListNode(parseInt);
                current = current.next;
            }
        }
        return head;
    }

    private Integer toInteger(ListNode head) {
        ListNode revertedHead = revert(head);
        StringBuilder sb1 = new StringBuilder();
        ListNode current = revertedHead;
        while (current != null) {
            sb1.append(current.val);
            current = current.next;
        }
        return Integer.valueOf(sb1.toString());
    }

    private ListNode revert(ListNode head) {
        ListNode current = head;
        ListNode pre = null;
        //反转
        //null -> A -> B -> C
        //while (current != null) {
        //    ListNode next = current.next;
        //    next.next = current;
        //
        //    current.next = pre;
        //    pre = current;
        //
        //    current = next;
        //}
        while (current != null) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    public static class ListNode {
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
