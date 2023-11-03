package com.cfysu.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/10/26
 */
public class RomeNum {

    @Test
    public void testRomeNum(){
        int ii = romanToInt("II");
        Assert.assertEquals(ii, 2);
    }

    public int romanToInt(String s) {
        int pre = getValue(s.charAt(0));
        int sum = pre;
        for (int i = 1;i<s.length();i++){
            int currentNum = getValue(s.charAt(i));
            if (pre < currentNum){
                //相减
                sum += (currentNum - 2 * pre);
            }else {
                //相加
                sum += currentNum;
            }
            pre = currentNum;
        }
        return sum;
    }
    private int getValue(char ch) { switch(ch) { case 'I': return 1; case 'V': return 5; case 'X': return 10; case 'L': return 50; case 'C': return 100; case 'D': return 500; case 'M': return 1000; default: return 0; } }
}
