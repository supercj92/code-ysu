package com.cfysu.base;

import org.junit.Test;

import java.io.IOException;

public class BitOperation {

    @Test
    public void testBitOperation(){
        int num = 5;
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(-num));
        System.out.println(5 >> 2);
        System.out.println(5 << 2);
        System.out.println(5 >>> 2);
        System.out.println(5 ^ 4);
        System.out.println(5 & 4);
        System.out.println(5 | 4);
    }
    @Test
    public void testExec() throws IOException {
        Process process = Runtime.getRuntime().exec("java");
    }
}
