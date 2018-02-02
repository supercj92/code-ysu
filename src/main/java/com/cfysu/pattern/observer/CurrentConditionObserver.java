package com.cfysu.pattern.observer;

public class CurrentConditionObserver implements Observer {

    private Subject subject;

    CurrentConditionObserver(Subject subject){
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("CurrentConditionObserver数据更新了：temp=" + temp + ";humidity=" + humidity + ";pressure=" + pressure);
    }
}
