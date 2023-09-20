package com.cfysu.lab.apache.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/9/18
 */
public class HttpMain {
    @Test
    public void testGet() {
        HttpGet httpGet = new HttpGet("http://dxm-pre-train-pdf.oss-cn-zhangjiakou.aliyuncs"
            + ".com/ca9b4a59dc126bc25efaf96b58ed3661.pdf?Expires=1695031804&OSSAccessKeyId=LTAI5tCyX15fkcbDoVbRWrqG"
            + "&Signature=oaBmiF87WqxduIGRo8OeLnF9n3U%3D");
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            FileOutputStream inputStream = new FileOutputStream("./test.pdf");
            IOUtils.copy(entity.getContent(), inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDownload() throws IOException {
        FileUtils.copyURLToFile(new URL("http://dxm-pre-train-pdf.oss-cn-zhangjiakou.aliyuncs"
            + ".com/ca9b4a59dc126bc25efaf96b58ed3661.pdf?Expires=1695031804&OSSAccessKeyId=LTAI5tCyX15fkcbDoVbRWrqG"
            + "&Signature=oaBmiF87WqxduIGRo8OeLnF9n3U%3D"), new File("./test2.pdf"));
    }
}
