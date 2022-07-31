package com.cfysu.martin.domain;

import java.util.Date;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public class RevenueRecognition {

    int amount;

    Date date;
    public RevenueRecognition(int amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    boolean isRecognizableBy(Date dateToCompare){
        return dateToCompare.after(date) || dateToCompare.equals(date);
    }

}
