package com.cfysu.datastructure;

import com.cfysu.datastructure.link.Link;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by cj on 17-6-17.
 */
public class LinkListTest {

    private static final Logger logger = Logger.getLogger(LinkListTest.class);

    @Test
    public void testLinkedList(){
        Link link = new Link();
        //在链表头部插入
        logger.info("在链表头部插入");
        link.insertFirst(1);
        link.insertFirst(2);
        link.insertFirst(3);
        link.insertFirst(4);
        link.displayList();
        //删除头节点
        logger.info("删除头节点");
        link.deleteFirst();
        link.displayList();
        //在尾部插入节点
        logger.info("在尾部插入节点");
        link.insertLast(100);
        link.displayList();
        //在尾部删除节点
        logger.info("在尾部删除节点");
        link.deleteLast();
        link.displayList();
    }
}
