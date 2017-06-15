package com.cfysu.junit.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(2);
		/*pool.submit(new Runnable() {
			int count = 0;
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(2000);
						System.out.println("A" + count++);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		
		pool.submit(new Runnable() {
			int count = 0;
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(2000);
						System.out.println("B" + count++);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});*/
		
		return;
	}

	@Test
	public void testArray(){
		String[] array = {};
		int length = array.length;
		if(length == 0){
			System.out.println("000");
		}
		System.out.println("ttttt");
	}
	
	private static boolean goThread(){
		new Thread(){
			@Override
			public void run(){
				int count = 0;
				while(true){
					try {
						Thread.sleep(2000);
						System.out.println("num:"+count);
						count++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
		System.out.println("sdfsafsfdasfsa");
		return false;
	}
}
