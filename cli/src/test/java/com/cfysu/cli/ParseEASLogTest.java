package com.cfysu.cli;

import java.io.IOException;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2022/8/11
 */
public class ParseEASLogTest {

    @Test
    public void testParseEAS() throws IOException {
        ParseEASLog parseEASLog = new ParseEASLog();
        String path = parseEASLog.parseEAS("/Users/chris/Documents/eas-service-statistic.log");
        System.out.println(path);
    }
}
