package com.cfysu.martin.script;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public class RecognitionService {

    @Autowired
    private DaoImpl dao;

    public void calculateRevenueRecognitions(long contractNum) throws SQLException {
        ResultSet contract = dao.findContract(contractNum);
        contract.next();
        int price = contract.getInt("price");
        java.sql.Date sign_date = contract.getDate("sign_date");
        String product = contract.getString("productId");
        if ("word".equals(product)){
            dao.insertRecognition(contractNum, price, sign_date);
        }else if ("excel".equals(product)){
            int amount = price / 2;

            dao.insertRecognition(contractNum, amount, sign_date);

            //入账时间推迟一个月
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.MONTH, 1);
            dao.insertRecognition(contractNum, amount, instance.getTime());
        }else if ("powerPoint".equals(product)){
            int amount = price / 3;
            dao.insertRecognition(contractNum, amount, sign_date);

            //入账时间推迟一个月
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.MONTH, 1);
            dao.insertRecognition(contractNum, amount, instance.getTime());

            //入账时间推迟一个月
            instance.add(Calendar.MONTH, 1);
            dao.insertRecognition(contractNum, amount, instance.getTime());
        }
    }

    public int recognizedRevenue(long contractNum, Date date) throws SQLException {
        ResultSet resultSet = dao.findRecognitionsFor(contractNum, date);
        int total = 0;
        while (resultSet.next()){
            total += resultSet.getInt("amont");
        }
        return total;
    }
}
