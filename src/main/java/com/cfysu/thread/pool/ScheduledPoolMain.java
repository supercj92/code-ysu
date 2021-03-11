package com.cfysu.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Author canglong
 * @Date 2019/10/17
 */
public class ScheduledPoolMain {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledPoolMain = Executors.newScheduledThreadPool(1,
            r -> {
            Thread thread = new Thread(r, "ScheduledPoolMain");
            thread.setDaemon(true);
            return thread;
        });

        ScheduledFuture<?> schedule = scheduledPoolMain.schedule(() -> System.out.println("scheduled task delay..."), 1000, TimeUnit.MILLISECONDS);

        schedule.cancel(false);
        System.out.println("--done--");
    }
}
