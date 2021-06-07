package com.cfysu.thread.concurrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author canglong
 * @Date 2021/5/28
 */
public class SimpleDateFormatMain {

    public static void main(String[] args) throws InterruptedException {

        int threadNum = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        AtomicInteger notSameCount = new AtomicInteger();

        for (int i = 0; i < threadNum; i++){
            new Thread(() -> {
                for (int j = 0; j < 10000; j++){
                    /**
                     * 有可能newDate和format不一致，当前线程格式化的date有可能是上个线程生成的。
                     * 导致当前线程格式化后的时间跟传入的不一致
                     */
                    Date newDate = new Date();
                    String format = simpleDateFormat.format(newDate);
                    //try {
                    //    //Date parseDate = simpleDateFormat.parse(format);
                    //    //if(!newDate.equals(parseDate)){
                    //    //    notSameCount.getAndIncrement();
                    //    //}
                    //} catch (ParseException e) {
                    //    e.printStackTrace();
                    //}
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("notSameCount:" + notSameCount.get());
    }
}
