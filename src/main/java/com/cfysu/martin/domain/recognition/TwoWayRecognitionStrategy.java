package com.cfysu.martin.domain.recognition;

import java.util.Calendar;

import com.cfysu.martin.domain.Contract;
import com.cfysu.martin.domain.RevenueRecognition;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public class TwoWayRecognitionStrategy implements RecognitionStrategy {

    int firstOffset;

    public TwoWayRecognitionStrategy(int firstOffset) {
        this.firstOffset = firstOffset;
    }

    @Override
    public void calculateRevenueRecognitions(Contract contract) {
        int price = contract.getProduct().getPrice();
        int amount = price / 2;
        contract.getRevenueRecognitionList().add(new RevenueRecognition(amount, contract.getSignDate()));

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, firstOffset);
        contract.getRevenueRecognitionList().add(new RevenueRecognition(amount, instance.getTime()));

    }
}
