package com.cfysu.thread.concurrent;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author canglong
 * @Date 2019/5/9
 * 每三秒生产一个，每一秒消费一个
 */
public class BlockingArray {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(4);

        //生产者
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = 0;
                blockingQueue.offer(++i);
            }
        }).start();

        //消费者
        while (true){
            try {
                //队列中没有值的话会等待最长1s
                System.out.println(blockingQueue.poll(1000L, TimeUnit.MILLISECONDS));
                System.out.println("blockingQueue size:" + blockingQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
