package com.cfysu.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/10/31
 */
public class RemoveDupNum {

    @Test
    public void testRemoveDuplicates(){
        int[] nums = new int[] {3,3};
        int i = removeDuplicates2(nums);
        Assert.assertEquals(i, 2);
    }

    public int removeDuplicates2(int[] nums) {
        int tail = 0;
        int preNum = nums[tail];
        for (int i = 1;i < nums.length;i++){
            if(preNum == nums[i]){
                //do nothing
            }else {
                tail++;
                nums[tail] = nums[i];
            }
            preNum = nums[i];
        }
        return tail + 1;
    }

    public int removeDuplicates(int[] nums) {
        //int head, tail = 0;
        int preNum = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < nums.length;i++){
            if(nums[i] == preNum){
                //tail++;
            }else {
                //去重数组
                list.add(nums[i]);
                //head = i;
            }
            preNum = nums[i];
        }
        Integer[] numInts = list.toArray(new Integer[list.size()]);
        int[] intArray = new int[numInts.length];

        for (int i = 0; i < numInts.length; i++) {
            intArray[i] = numInts[i].intValue();
        }
        nums = intArray;
        return list.size();
    }
}
