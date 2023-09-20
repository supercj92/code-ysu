package com.cfysu.algorithm;

import com.cfysu.algorithm.SortList.Node;

/**
 * @Author canglong
 * @Date 2023/9/11
 * [1,2,3,4,5], k=2 -> [2,1,4,3,5]
 * [1,2,3,4,5], k=3 -> [3,2,1,4,5]
 * [1,2,3,4,5,6,7,8], k=3 -> [3,2,1,6,5,4,7,8]
 * [1,2,3,4,5,6,7,8], k=4 -> [4,3,2,1,8,7,6,5]
 */
public class RevertList {
    public static void main(String[] args) {

    }

    public static void revertLink(Node head, int k) {
        Node current = head.next;
        int i = 0;
        int n = count(head);
        while (current != null) {
            int remainder = i % k;
            int halfLen = (k / 2);
            if (remainder < halfLen) {
                swap(head, i, n - i);
            }
            i++;
            current = current.next;
        }
    }

    public static void swap(Node head, int startIndex, int endIndex) {
        Node current = head;
        int i = 0;
        Node startPre = null;
        Node startAfter = null;
        Node start = null;

        Node endPre = null;
        Node endAfter = null;
        Node end = null;
        while (current != null) {
            if (i == (startIndex - 1)) {
                startPre = current;
            }
            if (i == startIndex) {
                start = current;
            }
            if (i == startIndex + 1) {
                startAfter = current;
            }

            if (i == (endIndex - 1)) {
                endPre = current;
            }
            if (i == endIndex) {
                end = current;
            }
            if (i == endIndex + 1) {
                endAfter = current;
            }

            //swap
            startPre.next = end;
            end.next = startAfter;

            endPre.next = start;
            start.next = endAfter;

            i++;
            current = current.next;
        }
    }

    public static int count(Node node) {
        return 0;
    }
}
