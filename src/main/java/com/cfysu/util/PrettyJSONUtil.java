package com.cfysu.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author eric
 * @Date 2018/12/10
 */
@Slf4j
public class PrettyJSONUtil {

    public static String toPrettyJson(String originJsonString){
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(originJsonString));
    }

    public static void main(String[] args) throws IOException {
        PrettyJSONUtil util = new PrettyJSONUtil();
        log.info("user:{}", util);
        Path prettyJsonPath = Paths.get("/Users/chris/Documents/prettyJson/prettyJson.json");
        byte[] bytes = Files.readAllBytes(prettyJsonPath);
        String prettyJson = PrettyJSONUtil.toPrettyJson(new String(bytes, 0, bytes.length, "utf-8"));
        Files.write(prettyJsonPath, prettyJson.getBytes("utf-8"), StandardOpenOption.WRITE);



        //Map<String, Map<String, Set<Long>>> functionGray = JSONObject.parseObject(jsonStr, new TypeReference<Map<String, Map<String,Set<Long>>>>(){});
    }


}
