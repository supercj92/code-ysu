package com.cfysu.martin.domain.recognition;

import com.cfysu.martin.domain.Contract;
import com.cfysu.martin.domain.RevenueRecognition;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public class CompleteRecognitionStrategy implements RecognitionStrategy {
    @Override
    public void calculateRevenueRecognitions(Contract contract) {
        contract.getRevenueRecognitionList().add(
            new RevenueRecognition(contract.getProduct().getPrice(), contract.getSignDate()));
    }
}
