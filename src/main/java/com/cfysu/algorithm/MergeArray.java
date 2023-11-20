package com.cfysu.algorithm;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/11/6
 */
public class MergeArray {

    @Test
    public void testMerge() {
        int[] nums1 = new int[] {1, 2, 3, 0, 0, 0};
        merge(nums1, 3, new int[] {2, 5, 6}, 3);
        System.out.println(JSON.toJSONString(nums1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int[] nums = new int[m + n];
        while (p1 < (m + n) && p2 < (n)) {
            if (nums1[p1] <= nums2[p2] && p1 < m) {
                insertArray(nums, p3, nums1[p1]);
                p1++;
            } else {
                insertArray(nums, p3, nums2[p2]);
                p2++;
            }
            p3++;
        }

        System.arraycopy(nums, 0, nums1, 0, m + n);
    }

    private void insertArray(int[] nums, int index, int val) {
        nums[index] = val;
    }
}
