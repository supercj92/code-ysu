package com.cfysu.lab.jdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2022/1/21
 */
public class LangMain {

    @Test
    public void testMather(){
        Pattern compile = Pattern.compile("\\d");
        Matcher matcher = compile.matcher("123");
        //System.out.println(matcher.find());
        String s = matcher.replaceAll("a");
        System.out.println(s);
    }
}
