package com.cfysu.spring.reflect;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ContextMain {
    public static void main(String[] args) {
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("file:/Users/chris/IdeaProjects/mine/code-ysu/target/classes/ApplicationContext.xml");
        OperateDataMain operateDataMain = (OperateDataMain)applicationContext.getBean("operateDataMain");
        operateDataMain.insertData();
    }
}
