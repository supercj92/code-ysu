package com.cfysu.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/11/1
 */
public class RemoveDup {

    @Test
    public void testRemoveElement(){
        int i = removeElement(new int[] {3, 3}, 3);
        Assert.assertEquals(2, i);
    }

    public int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;
        while (i < nums.length && i < j){
            if(val == nums[i]){
                while (nums[j] == val){
                    j--;
                    if(j < 1){
                        break;
                    }
                }
                if (j <= i){
                    break;
                }
                //swap
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
            i++;
        }
        return j;
    }
}
