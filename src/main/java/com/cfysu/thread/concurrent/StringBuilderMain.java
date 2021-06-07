package com.cfysu.thread.concurrent;

import java.security.InvalidParameterException;
import java.util.concurrent.CountDownLatch;

import org.junit.Assert;

/**
 * @Author canglong
 * @Date 2021/5/28
 */
public class StringBuilderMain {

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        ChangeableString changeableString = new ChangeableString(ChangeableString.SAFE);
        int threadNum = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++){
            new Thread(() -> {
                for (int j = 0; j < 10000; j++){
                    //stringBuilder.append("a");
                    changeableString.append("a");
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("cost:" + (endTime - startTime));
        Assert.assertEquals(1000000, changeableString.length());
    }

    static class ChangeableString {

        private String safeMode;

        public static final String SAFE = "safe";
        public static final String UNSAFE = "unsafe";
        private StringBuffer safeString;
        private StringBuilder unsafeString;

        public ChangeableString(String safeMode){
            if(!SAFE.equals(safeMode) && !UNSAFE.equals(safeMode)){
                throw new InvalidParameterException(safeMode + " is invalid");
            }
            this.safeMode = safeMode;
            if(SAFE.equals(this.safeMode)){
                safeString = new StringBuffer();
            }else {
                unsafeString = new StringBuilder();
            }
        }

        public void append(String string){
            if(SAFE.equals(this.safeMode)){
                safeString.append(string);
            }else {
                unsafeString.append(string);
            }
        }

        public int length(){
            if(SAFE.equals(this.safeMode)){
                return safeString.length();
            }else {
                return unsafeString.length();
            }
        }
    }
}
