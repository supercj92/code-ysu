package com.cfysu.spring.reflect;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OperateDataService {

    //@Autowired
    //private TestBeanPostProcessor testBeanPostProcessor;

    @Value("${key_1}")
    private String key;

    @Authorization("admin")
    public Object updateData(){
        System.out.println("admin update data...");
        return 1;
    }

    @Authorization("visitor")
    public void selectData(){
        System.out.println("visitor select data...");
    }

    @Authorization("superAdmin")
    public void deleteData(){
        System.out.println("superAdmin delete from table...");
    }

    public void insertData(){
        System.out.println("insert data...");
    }
}
