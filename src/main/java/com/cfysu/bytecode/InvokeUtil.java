package com.cfysu.bytecode;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author canglong
 * @Date 2021/4/7
 */
@Slf4j
public class InvokeUtil {

    public static JSONObject doInvoke(JSONObject jsonObject){
        log.info("--InvokeUtil.invoke--:{}", jsonObject);
        JSONObject res = new JSONObject();
        res.put("resKey", "res");
        return res;
    }
}
