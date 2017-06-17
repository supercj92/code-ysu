package com.cfysu.datastructure;

import com.cfysu.datastructure.list.LinkedList;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by cj on 17-6-17.
 */
public class LinkListTest {

    private static final Logger logger = Logger.getLogger(LinkListTest.class);

    @Test
    public void testLinkedList(){
        LinkedList linkedList = new LinkedList();
        //在链表头部插入
        logger.info("在链表头部插入");
        linkedList.insertFirst(1);
        linkedList.insertFirst(2);
        linkedList.insertFirst(3);
        linkedList.insertFirst(4);
        linkedList.displayList();
        //删除头节点
        logger.info("删除头节点");
        linkedList.deleteFirst();
        linkedList.displayList();
        //在尾部插入节点
        logger.info("在尾部插入节点");
        linkedList.insertLast(100);
        linkedList.displayList();
        //在尾部删除节点
        logger.info("在尾部删除节点");
        linkedList.deleteLast();
        linkedList.displayList();
    }
}
