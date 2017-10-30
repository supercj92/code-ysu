package com.cfysu.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCommunicationByCondition {
	public static void main(String[] args){
		final Lock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				for (int i = 0;i < 3;i++){
					System.out.println(Thread.currentThread().getName() + ":" + i);
					if(i == 0){
						try {
							System.out.println("A释放锁，进入等待池。。。");
							condition.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				lock.unlock();
			}
		}, "threadA");

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				for (int i = 0;i < 3;i++){
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
				System.out.println("唤醒A线程");
				condition.signal();
				lock.unlock();
			}
		}, "threadB");

		threadA.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadB.start();
	}
}
