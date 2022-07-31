package com.cfysu.martin.domain.recognition;

import com.cfysu.martin.domain.Contract;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public interface RecognitionStrategy {
    void calculateRevenueRecognitions(Contract contract);
}
