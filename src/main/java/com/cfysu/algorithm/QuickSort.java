package com.cfysu.algorithm;

import java.util.Arrays;

/**
 * 快排基于划分的思想
 */
public class QuickSort {

    private static int[] theArray = {3, 7, 1, 8, 6, 9, 2, 5, 0, 5};

    public static void quickSort(int left, int right) {
        if (right - left <= 0) {
            return;
        } else {
            int pivot = theArray[right];
            int partition = partition(left, right, pivot);
            System.out.println("after partition:" + Arrays.toString(theArray));
            quickSort(left, partition - 1);
            quickSort(partition + 1, right);
        }
    }

    /**
     * 划分数组第一版
     *
     * @param theArray
     */
    public static void partition(int[] theArray) {
        int leftPtr = -1;
        int rightPtr = theArray.length;
        int pivot = theArray[rightPtr - 1];

        while (true) {

            while (leftPtr < rightPtr && theArray[++leftPtr] <= pivot) {

            }
            System.out.print("leftPtr is stopped at :" + leftPtr);

            while (rightPtr > leftPtr && theArray[--rightPtr] >= pivot) {

            }
            System.out.println("   rightPtr is stopped at:" + rightPtr);

            if (leftPtr >= rightPtr) {
                System.out.println(leftPtr + ":left <--partition end--> right:" + rightPtr);
                break;
            } else {
                swap(leftPtr, rightPtr);
            }
        }

    }

    /**
     * 划分算法第二版
     *
     * @param left
     * @param right
     * @param pivot
     * @return
     */
    public static int partition(int left, int right, int pivot) {

        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {

            while (leftPtr < rightPtr && theArray[++leftPtr] <= pivot)
                ;
            System.out.print("leftPtr is stopped at:" + theArray[leftPtr]);

            while (rightPtr > leftPtr && theArray[--rightPtr] >= pivot)
                ;
            System.out.println("   rightPtr is stopped at:" + theArray[rightPtr]);

            if (leftPtr >= rightPtr) {
                System.out.println(theArray[leftPtr] + ":left <--partition end--> right:" + theArray[rightPtr]);
                break;
            } else {
                swap(leftPtr, rightPtr);
                System.out.println(theArray[leftPtr] + " swap " + theArray[rightPtr]);
            }
        }
        swap(leftPtr, right);
        System.out.println(theArray[leftPtr] + " swap " + theArray[right]);
        return leftPtr;
    }

    public static void swap(int leftIndex, int rightIndex) {
        int temp = theArray[leftIndex];
        theArray[leftIndex] = theArray[rightIndex];
        theArray[rightIndex] = temp;
    }

    public static void main(String[] args) {
        System.out.println("before sort:" + Arrays.toString(theArray));
        //partition(theArray);
        quickSort(0, theArray.length - 1);
        System.out.println("after sort:" + Arrays.toString(theArray));
    }
}
