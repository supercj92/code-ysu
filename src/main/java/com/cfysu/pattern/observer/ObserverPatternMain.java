package com.cfysu.pattern.observer;

public class ObserverPatternMain {
    public static void main(String[] args){
        WeatherDataSubject subject = new WeatherDataSubject();
        Observer currentConditionObserver = new CurrentConditionObserver(subject);
        Observer forecastObserver = new ForecastObserver(subject);

        subject.setHumidity(11.1f);
        subject.setPressure(12.1f);
        subject.setTemp(13.1f);
        //数据更新，通知观察者
        subject.notifyObserver();
    }
}
