package com.cfysu.algorithm;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/11/6
 */
public class PlusOneTest {

    @Test
    public void testPlusOne() {
        plusOne(new int[] {1, 2, 3, 4});
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }

        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}
