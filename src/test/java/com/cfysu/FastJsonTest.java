package com.cfysu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * @Author canglong
 * @Date 2021/6/18
 */
public class FastJsonTest {

    public static final SerializeConfig SERIALIZE_CONFIG = new SerializeConfig(true);

    public static void main(String[] args) {

        FastJsonTest fastJsonTest = new FastJsonTest();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            while (true){
                JSON.toJSONString(new TestData(), new SerializeConfig(true));
            }
        });
        //executorService.submit(() -> {
        //    while (true){
        //        JSON.toJSONString(new TestData(), SERIALIZE_CONFIG);
        //    }
        //});
    }

    static class TestData{
        private String attr;
    }
}
