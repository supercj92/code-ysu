package com.cfysu.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/10/26
 */
public class LongestPrefix {

    @Test
    public void testLongestCommonPrefix(){
        String s = longestCommonPrefix(new String[] {"flower", "flow", "flight"});
        Assert.assertEquals(s, "fl");
    }

    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1;i < strs.length;i++){
            prefix = findLongestSubString(prefix, strs[i]);
            System.out.println(prefix);
        }
        return prefix;
    }

    private String findLongestSubString(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len) {
            if (str1.charAt(index) == str2.charAt(index)){
                index++;
            }else {
                break;
            }
        }
        return str1.substring(0, index);
    }
}
