package com.cfysu.martin.domain;

import com.cfysu.martin.domain.recognition.CompleteRecognitionStrategy;
import com.cfysu.martin.domain.recognition.RecognitionStrategy;
import com.cfysu.martin.domain.recognition.ThreeWayRecognitionStrategy;
import com.cfysu.martin.domain.recognition.TwoWayRecognitionStrategy;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public class Product {

    String name;
    int price;
    RecognitionStrategy recognitionStrategy;

    public Product(String name, int price, RecognitionStrategy recognitionStrategy){
        this.name = name;
        this.recognitionStrategy = recognitionStrategy;
        this.price = price;
    }

    public static Product buildWordProduct(){
        return new Product("word", 10, new CompleteRecognitionStrategy());
    }

    public static Product buildExcelProduct(){
        return new Product("excel", 20, new TwoWayRecognitionStrategy(30));
    }

    public static Product buildPowerPoint(){
        return new Product("powerPoint", 30, new ThreeWayRecognitionStrategy(30, 60));
    }

    void calculateRevenueRecognitions(Contract contract){
        recognitionStrategy.calculateRevenueRecognitions(contract);
    }

    public int getPrice() {
        return price;
    }
}
