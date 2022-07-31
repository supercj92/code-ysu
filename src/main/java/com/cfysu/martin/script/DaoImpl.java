package com.cfysu.martin.script;

import java.sql.ResultSet;
import java.util.Date;

/**
 * @Author canglong
 * @Date 2022/6/29
 */
public class DaoImpl {

    public void insertRecognition(long contractNum, int amount, Date date) {
        //insert into revenue_recognition values (contractNum, amount, date)
    }

    public ResultSet findRecognitionsFor(long contractNum, Date date) {
        //select amount from revenue_recognition where contractNum = ${contractNum} and date <= ${date}
        return null;
    }

    public ResultSet findContract(long contractNum) {
        //select * from contract c, product p where c.contractNum = ${contractNum} and c.productId = p.id
        return null;
    }

}
