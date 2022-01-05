package com.cfysu.http;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * @Author canglong
 * @Date 2021/12/16
 */
public class OKHttpMain {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
            .url("http://localhost:443/rest/userInfo")
            .post(null)
            .addHeader("content-type", "application/x-www-form-urlencoded")
            .addHeader("cache-control", "no-cache")
            .addHeader("postman-token", "74d555be-3bb9-44ac-acc4-1bc55a0756cb")
            .build();

        Response response = client.newCall(request).execute();
    }
}
