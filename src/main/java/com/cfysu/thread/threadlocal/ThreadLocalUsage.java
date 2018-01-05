package com.cfysu.thread.threadlocal;

public class ThreadLocalUsage {
    public static void main(String[] args){
        User user = new User("jack");
        new Thread(new Handler(user)).start();
        new Thread(new Handler(user)).start();
        System.out.println(Thread.currentThread().getName() + ":" + user.getName());
    }
}

class Handler implements Runnable{

    private User user;

    Handler(User user){
        this.user = user;
    }

    public void run() {
        user.setName(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + ":" + user.getName());
    }
}
