package com.cfysu.lab.jdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2022/1/21
 */
public class LangMain {

    @Test
    public void testMather() {
        Pattern compile = Pattern.compile("\\d");
        Matcher matcher = compile.matcher("123");
        //System.out.println(matcher.find());
        String s = matcher.replaceAll("a");
        System.out.println(s);
    }

    @Test
    public void testQueue() throws InterruptedException {
        //BlockingQueue<Integer> blockingDeque = new ArrayBlockingQueue<>(2);
        //blockingDeque.put(1);
        //Assert.assertEquals(blockingDeque.size(), 1);
        //Assert.assertFalse(blockingDeque.isEmpty());
        //blockingDeque.remove(1);
        //Assert.assertTrue(blockingDeque.isEmpty());

        String substring
            = ("/Users/chris/Library/Java/JavaVirtualMachines/corretto-1.8.0_332/Contents/Home/bin/java -ea -Didea"
            + ".test.cyclic.buffer").substring(
            0, 64);
        System.out.println(substring);

        Assert.assertEquals(64, substring.length());

    }
}
