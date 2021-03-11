package com.cfysu.datastructure;

import com.cfysu.datastructure.queue.ArrayQueue;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by cj on 17-6-18.
 */
public class ArrayQueueTest {

    private static final Logger LOGGER = Logger.getLogger(ArrayQueueTest.class);

    @Test
    public void testQueue(){
        ArrayQueue queue = new ArrayQueue(4);

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);

        LOGGER.info("删除元素");
        for (int i = 0;i < 3;i++){
            System.out.print(queue.remove() + ", ");
        }
    }
}
