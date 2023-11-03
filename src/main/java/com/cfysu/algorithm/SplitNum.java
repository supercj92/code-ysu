package com.cfysu.algorithm;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/10/26
 */
public class SplitNum {

    @Test
    public void testSplit() {
        int num = 123456;
        int[] nums = new int[6];
        int size  = 0;
        while (num > 0) {
            nums[size++] = num % 10;
            num = num / 10;
        }
        System.out.println(JSON.toJSONString(nums));

        int numRevert = 0;
        for (int j : nums) {
            numRevert = numRevert * 10 + j;
        }
        System.out.println(numRevert);
    }
}
