package com.cfysu.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/11/2
 */
public class FindSubString {

    @Test
    public void testSubStr() {
        int i = subStr("leetcode", "leeto");
        Assert.assertEquals(1, i);
    }

    public int subStr(String haystack, String needle) {
        char[] hayChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        boolean findSubString = false;
        for (int i = 0; i <= (hayChars.length - needleChars.length); i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (hayChars[i + j] != needleChars[j]) {
                    break;
                }
                if (j == needleChars.length - 1) {
                    findSubString = true;
                }
            }
            if (findSubString) {
                return i;
            }
        }
        return -1;
    }
}
