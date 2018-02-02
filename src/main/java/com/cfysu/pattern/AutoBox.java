package com.cfysu.pattern;

import java.io.IOException;

public class AutoBox {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        //Long sum = 0L;这样会反复生成Long对象，占用内存
        long sum = 0;//相对上面占用非常少的内存
        for (int i = 0;i < Integer.MAX_VALUE;i++){
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("总和:%d,耗时:%d", sum, (endTime - startTime)));

        System.in.read();
    }
}
