package com.cfysu.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cj on 2017/9/5.
 */
public class HttpByJava {

    @Test
    public void postByJavaNet(){
        BufferedReader bufferedReader = null;
        try {
            URL receiveMsg = new URL("http://taobao.com");
            HttpURLConnection connection = (HttpURLConnection)receiveMsg.openConnection();
            //设置http请求头信息
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            //post
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter pw = new PrintWriter(connection.getOutputStream());
            String[] pins = new String[]{};
            String[] tokens = new String[]{};
            //System.out.println("post data:" + parm);
            //pw.print(parm);
            pw.flush();

            if(connection.getResponseCode() == 500){
                System.out.println("500:" + connection.getResponseMessage());
                return;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String buffer = null;
            while ((buffer = bufferedReader.readLine()) != null){
                stringBuffer.append(buffer);
            }
            System.out.println("response:" + stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getByApache(){
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:8080/test/httpHeader");

        NameValuePair[] nameValuePair = new NameValuePair[]{};
        getMethod.setQueryString(nameValuePair);

        try {
            httpClient.executeMethod(getMethod);
            if(getMethod.getStatusCode() == HttpStatus.SC_OK){
                System.out.println("response:" + getMethod.getResponseBodyAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
