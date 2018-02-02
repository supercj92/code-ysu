package com.cfysu.pattern.observer;

public class ForecastObserver implements Observer {

    private Subject subject;

    ForecastObserver(Subject subject){
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("ForecastObserver数据更新了：temp=" + temp + ";humidity=" + humidity + ";pressure=" + pressure);
    }
}
