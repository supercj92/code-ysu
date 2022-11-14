package com.cfysu.cli;

import java.io.IOException;

import org.junit.Test;

/**
 * @Author canglong
 * @Date 2022/8/1
 */
public class ParseAIEngineLogTest {

    @Test
    public void testParse() throws IOException {
        ParseAIEngineLog aiEngineLog = new ParseAIEngineLog();
        aiEngineLog.parse("/Users/chris/Documents/aliyun-pre.log", "");
    }
}
