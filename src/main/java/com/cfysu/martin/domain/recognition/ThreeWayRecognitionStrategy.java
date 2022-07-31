package com.cfysu.martin.domain.recognition;

import java.util.Calendar;

import com.cfysu.martin.domain.Contract;
import com.cfysu.martin.domain.RevenueRecognition;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public class ThreeWayRecognitionStrategy implements RecognitionStrategy{

    int firstOffset;
    int secondOffset;

    public ThreeWayRecognitionStrategy(int firstOffset, int secondOffset) {
        this.firstOffset = firstOffset;
        this.secondOffset = secondOffset;
    }

    @Override
    public void calculateRevenueRecognitions(Contract contract) {
        int price = contract.getProduct().getPrice();
        int amount = price / 3;
        contract.getRevenueRecognitionList().add(new RevenueRecognition(amount, contract.getSignDate()));

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, firstOffset);
        contract.getRevenueRecognitionList().add(new RevenueRecognition(amount, instance.getTime()));

        instance.add(Calendar.DAY_OF_MONTH, secondOffset);
        contract.getRevenueRecognitionList().add(new RevenueRecognition(amount, instance.getTime()));
    }
}
