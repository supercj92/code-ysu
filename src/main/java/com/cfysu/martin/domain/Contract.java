package com.cfysu.martin.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public class Contract {

    List<RevenueRecognition> revenueRecognitionList = new ArrayList<>();
    Product product;
    Date signDate;
    int id;

    public Contract(Product product, Date signDate) {
        this.product = product;
        this.signDate = signDate;
    }

    public int recognizedRevenue(Date date){
        int total = 0;
        for (RevenueRecognition revenueRecognition : revenueRecognitionList){
            if (revenueRecognition.isRecognizableBy(date)){
                total += revenueRecognition.getAmount();
            }
        }
        return total;
    }

    public void calculateRecognitions(){
        product.calculateRevenueRecognitions(this);
    }

    public List<RevenueRecognition> getRevenueRecognitionList() {
        return revenueRecognitionList;
    }

    public Date getSignDate() {
        return signDate;
    }

    public Product getProduct() {
        return product;
    }
}
