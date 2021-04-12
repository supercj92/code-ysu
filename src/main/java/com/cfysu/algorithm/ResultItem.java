package com.cfysu.algorithm;

import lombok.Data;

/**
 * @Author canglong
 * @Date 2021/3/15
 */
@Data
public class ResultItem {

    private String str1;

    private String str2;

    private int int1;

    private double double2;

    public ResultItem(String str1, String str2, int int1, double double2){
        this.str1 = str1;
        this.str2 = str2;
        this.int1 = int1;
        this.double2 = double2;
    }

    public boolean isBigger(ResultItem resultItem){
        //todo resultItem比较
        return false;
    }
}
