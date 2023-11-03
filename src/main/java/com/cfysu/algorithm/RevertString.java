package com.cfysu.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/10/18
 */
public class RevertString {

    @Test
    public void testReverseStr() {
        String res = reverseStr("abcdefg", 2);
        Assert.assertEquals("bacdfeg", res);

        String res2 = reverseStr("abcdefg", 8);
        Assert.assertEquals("gfedcba", res2);
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            int num = i + 1;
            int groupNum = calculateGroupNum(num, k);
            //奇数区间
            if (groupNum % 2 != 0) {
                //区间的结尾
                if ((num % k) == 0 || (i == (chars.length - 1) && chars.length < k)) {
                    endIndex = i;
                    revertArray(chars, startIndex, endIndex);
                }
            }
            //偶数区间
            else {
                //区间的结尾
                if ((num % k) == 0) {
                    startIndex = (i + 1);
                }
            }
        }
        return new String(chars);
    }

    private void revertArray(char[] chars, int startIndex, int endIndex) {
        //反转指定区间的字符串
        int len = endIndex - startIndex;
        int end = (startIndex + len / 2);
        int tailIndex = endIndex;
        for (int i = startIndex; i <= end; i++) {
            char head = chars[i];
            char tail = chars[tailIndex];
            chars[i] = tail;
            chars[tailIndex] = head;
            tailIndex--;
        }
    }

    private int calculateGroupNum(int i, int groupSize) {
        int groupNum = i % groupSize == 0 ? i / groupSize : (i / groupSize) + 1;
        return groupNum;
    }

    @Test
    public void testCalculateGroupNum() {
        Assert.assertEquals(1, calculateGroupNum(1, 3));
        Assert.assertEquals(1, calculateGroupNum(2, 3));
        Assert.assertEquals(1, calculateGroupNum(3, 3));
        Assert.assertEquals(2, calculateGroupNum(4, 3));
    }

    @Test
    public void testRevertArray() {
        char[] chars = new char[]{'a', 'b', 'c', 'd'};
        revertArray(chars, 0, 1);
        Assert.assertEquals("bacd", new String(chars));

        chars = new char[]{'a', 'b', 'c', 'd'};
        revertArray(chars, 0, 2);
        Assert.assertEquals("cbad", new String(chars));

        chars = new char[]{'a', 'b', 'c', 'd'};
        revertArray(chars, 0, 3);
        Assert.assertEquals("dcba", new String(chars));

        chars = new char[]{'a', 'b', 'c', 'd'};
        revertArray(chars, 1, 3);
        Assert.assertEquals("adcb", new String(chars));
    }
}
