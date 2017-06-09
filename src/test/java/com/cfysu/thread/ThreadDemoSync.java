package com.cfysu.thread;

public class ThreadDemoSync {
    public static void main(String[] args){
        Ticket t =new Ticket();
        Thread t1 = new Thread(t,"窗口一");
        Thread t2 = new Thread(t,"窗口二");
        Thread t3 = new Thread(t,"窗口三");
        Thread t4 = new Thread(t,"窗口四");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
class Ticket implements Runnable{
    private int ticket = 10;
    public void run(){
        while(true){
            synchronized (new Object()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(ticket<=0)
                    break;
                ticket--;
                System.out.println(Thread.currentThread().getName()+"--票数为--:"+ticket);
            }
        }
    }
}