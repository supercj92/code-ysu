package com.cfysu.lab.spring.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * @Author canglong
 * @Date 2022/1/13
 */
public class RestTemplateMain {
    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();
        Map<String, String> vars = new HashMap<>();
        vars.put("k1", "v1");
        ResponseEntity<String> forEntity = template.getForEntity("http://www.taobao.com", String.class, vars);
        System.out.println();

        RequestEntity<String> request = RequestEntity.post("http://www.taobao.com").contentType(MediaType.TEXT_PLAIN)
            .body(
                "hi taobao");
        ResponseEntity<String> exchange = template.exchange(request, String.class);
        System.out.println(exchange);
    }

    @Test
    public void testSSE() {
        //RestTemplate template = new RestTemplate();

        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate template = builder.setReadTimeout(Duration.of(20, SECONDS)).build();

        String url
            = "http://localhost/chat/qianwen?query=介绍下杭州，不少于800字";
        //String url = "http://localhost/chat/stream-sse";
        //String url = "http://localhost/chat/qianwen?query=%E4%BD%A0%E6%98%AF%E8%B0%81";
        template.execute(url,
            HttpMethod.GET, request -> {
            }, response -> {
                //StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
                //String read = stringHttpMessageConverter.read(String.class, response);
                //System.out.println(read);
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
                String line;
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        //System.out.println("Got some data, let's use my ObjectMapper to parse into something
                        // useful!");
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    //Something clever
                }
                //int len;
                //int line = 0;
                //InputStreamReader reader = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
                //char[] buffer = new char[128];
                //while (true) {
                //    try {
                //        if (!((len = reader.read(buffer)) > 0)) {break;}
                //        String reply = new String(buffer, 0, len);
                //        System.out.println("new line-->> " + line++ + ":" + reply);
                //    } catch (IOException e) {
                //        throw new RuntimeException(e);
                //    }
                //}
                return response;
            });
    }

    @Test
    public void testSSETemplate() {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> request = RequestEntity.get(
            "http://localhost/chat/qianwen?query=%E4%BB%8B%E7%BB%8D%E4%B8%8B%E6%9D%AD"
                + "%E5%B7"
                + "%9E%EF%BC%8C%E4%B8%8D%E5"
                + "%B0%91%E4%BA%8E800%E5%AD%97").accept(MediaType.TEXT_HTML, MediaType.IMAGE_GIF, MediaType.IMAGE_JPEG,
            MediaType.ALL).acceptCharset(
            StandardCharsets.UTF_8).build();

        ResponseEntity<String> exchange = restTemplate.exchange(request, String.class);
        System.out.println(exchange);
    }

    @Test
    public void testJavaHttp() throws URISyntaxException, IOException {
        String urlStr
            = "http://localhost/chat/qianwen?query=%E4%BB%8B%E7%BB%8D%E4%B8%8B%E6%9D%AD%E5%B7%9E%EF%BC%8C%E4%B8%8D%E5"
            + "%B0%91%E4%BA%8E800%E5%AD%97";
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            //System.out.println("Got some data, let's use my ObjectMapper to parse into something
            // useful!");
            System.out.println(line);
        }
    }
}
