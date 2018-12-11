package com.cfysu.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author eric
 * @Date 2018/12/10
 */
public class PrettyJSONUtil {

    public static String toPrettyJson(String originJsonString){
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(originJsonString));
    }

    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("/auth.json"));
        System.out.println(PrettyJSONUtil.toPrettyJson(new String(bytes, 0, bytes.length, "utf-8")));
    }
}
