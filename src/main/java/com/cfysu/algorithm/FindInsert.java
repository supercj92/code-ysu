package com.cfysu.algorithm;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/11/2
 */
public class FindInsert {

    @Test
    public void testSearchInsert() {
        int i = searchInsert(new int[] {1, 2, 3, 4}, 1);
        System.out.println(i);
    }

    public int searchInsert(int[] nums, int target) {
        //return findTarget(0, nums.length - 1, nums, target);
        return searchInsert2(nums, target);
    }

    public int searchInsert2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int midIndex = (right - left) / 2 + left;
            int midVal = nums[midIndex];
            if (midVal > target){
                right = midIndex;
            }else if (midVal == target){
                return midIndex;
            }
            else {
                left = midIndex;
            }
        }
        return left;
        //return findTarget(0, nums.length - 1, nums, target);
    }

    private int findTarget(int start, int end, int[] nums, int target) {
        int midIndex = (end - start) / 2 + start;
        int midVal = nums[midIndex];
        if (((end - start) == 1) || start == end){
            if (nums[end] <= target){
                return end + 1;
            }else if(target <= nums[start]){
                return start;
            }
            return end;
        }
        if (midVal == target) {
            return midIndex;
        }
        if (midVal > target) {
            return findTarget(start, midIndex, nums, target);
        } else {
            return findTarget(midIndex, end, nums, target);
        }
    }
}
