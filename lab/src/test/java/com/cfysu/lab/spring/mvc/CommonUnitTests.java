package com.cfysu.lab.spring.mvc;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.jupiter.api.Test;

/**
 * @Author canglong
 * @Date 2022/1/17
 */
public class CommonUnitTests {

    @Test
    public void testURL() throws MalformedURLException, URISyntaxException {
        URL url = new URL("https://n.alibaba-inc.com/cloudAccountManage/ramUserManageNew?spm=a1znmd.11230133.page.40.fc9e2889EmBhg1");
        URI uri = url.toURI();
        System.out.println();
    }
}
