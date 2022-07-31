package com.cfysu.martin.domain;

import java.util.Date;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public class Main {
    public static void main(String[] args) {
        Date lastMonth = null;
        Contract excelContract = new Contract(Product.buildExcelProduct(), lastMonth);
        excelContract.calculateRecognitions();
        int recognizedRevenue = excelContract.recognizedRevenue(new Date());

        Date someDay = null;
        Contract powerPointContract = new Contract(Product.buildPowerPoint(), someDay);
        powerPointContract.calculateRecognitions();
        int pptRecognizedRevenue = powerPointContract.recognizedRevenue(new Date());
    }
}
