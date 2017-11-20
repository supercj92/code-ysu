package com.cfysu.proxy;

public class DogImpl implements Dog{

    private String name ="小白";

    @Override
    public void bark(String str) {
        System.out.println("汪汪汪。。。" + str);
    }
    private void eat(String food){
        System.out.println("dog eat " + food);
    }
}
