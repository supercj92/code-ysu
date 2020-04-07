package com.cfysu.spring.reflect;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ContextMain {
    public static void main(String[] args) {
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("file:/Users/chris/IdeaProjects/mine/code-ysu/target/classes/ApplicationContext.xml");
        OperateDataService operateDataMain = (OperateDataService)applicationContext.getBean("operateDataMain");
        operateDataMain.insertData();
        Object testUtil = applicationContext.getBean("testUtil");
        if(testUtil == null){
            System.out.println("testUtil is null");
        }else {
            System.out.println(testUtil.toString());
        }
    }
}
