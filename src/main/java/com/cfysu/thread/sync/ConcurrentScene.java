package com.cfysu.thread.sync;

public class ConcurrentScene {
    public static void main(String[] args){
        Account account = new Account(1000);
        People peopleA = new People(account);
        People peopleB = new People(account);

        testlabel://java中标签没什么用,break,continue
        http://www.jd.com

        new Thread(peopleA, "peopleA").start();
        new Thread(peopleB, "peopleB").start();
    }
}

class People implements Runnable{

    private Account account;

    public People(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        account.takeMoneyFromAccount(800);
    }
}
