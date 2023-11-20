package com.cfysu.algorithm;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/11/3
 */
public class LastWord {

    @Test
    public void testLengthOfLastWord(){
        System.out.println(lengthOfLastWord("World"));
    }

    public int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int tail = chars.length;
        int head = 0;
        boolean findTail = false;
        for (int i = tail - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                if (findTail) {
                    head = i;
                    break;
                }
            } else {
                if (!findTail) {
                    tail = i;
                    findTail = true;
                }
            }
        }
        if (chars[head] == ' '){
           return tail - head;
        }
        return tail - head + 1;
    }
}
