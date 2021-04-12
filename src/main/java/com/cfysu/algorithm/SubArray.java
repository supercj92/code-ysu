package com.cfysu.algorithm;

import java.security.InvalidParameterException;

/**
 * @Author canglong
 * @Date 2021/3/24
 * 在给定顺序array中找到最大子array
 */
public class SubArray {

    public int[] findSubArray(int[] sortedArray, int size){
        int start = 0;
        int end = 0;
        int arrayLength = sortedArray.length;
        int maxSum = 0;

        //find max subArray
        for(int i = 0;i < (arrayLength - size);i++){
            int temp = sumArray(sortedArray, i, i + size);
            if(temp > maxSum){
                maxSum = temp;
                start = i;
                end = start + size;
            }
        }

        //剪切数组
        int[] targetArray = new int[size];
        System.arraycopy(sortedArray, start, targetArray, 0, size);
        return targetArray;
    }

    private int sumArray(int[] sortedArray, int start, int end){
        int lastIndex =  sortedArray.length - 1;
        if(start < 0 || start > lastIndex || end < start || end > lastIndex){
            throw new InvalidParameterException();
        }

        int sum = 0;
        for(int i = start;i <= end;i++){
            sum += sortedArray[i];
        }

        return sum;
    }
}
