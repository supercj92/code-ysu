package com.cfysu.lab.eflops;

/**
 * @Author canglong
 * @Date 2023/9/4
 */
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.dsig.SignatureMethod;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author 筱井 [yuhua.zyh@alibaba-inc.com]
 * @since 2023-04-28
 */
public class EflopsHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        try {
            String ak = request.getHeaders().get("eflops-ak").iterator().next();
            String sk = request.getHeaders().get("eflops-sk").iterator().next();
            String strToSign = getStringToSign(request.getMethodValue(), request.getURI().getPath(),
                request.getHeaders().toSingleValueMap(), request.getURI().getQuery());
            String signature = sign(strToSign, sk);
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, "acs " + ak + ":" + signature);
            request.getHeaders().remove("eflops-ak");
            request.getHeaders().remove("eflops-sk");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return execution.execute(request, body);
    }

    private String getStringToSign(String method, String path, Map<String, String> headers,
        String queryString) {
        Map<String, String> lowerCaseHeaders = Maps.newHashMap();
        for (Entry<String, String> stringStringEntry : headers.entrySet()) {
            lowerCaseHeaders.put(stringStringEntry.getKey().toLowerCase(), stringStringEntry.getValue());
        }
        String httpMethod = method.toUpperCase();
        String accept = lowerCaseHeaders.getOrDefault("accept", "");
        String contentMd5 = lowerCaseHeaders.getOrDefault("content-md5", "");
        String contentType = lowerCaseHeaders.getOrDefault("content-type", "");
        String date = lowerCaseHeaders.getOrDefault("date", "");
        String canonicalizedHeaders = getCanonicalizedHeaders(lowerCaseHeaders);
        String canonicalizedResource = getCanonicalizedResouce(path, queryString);
        return httpMethod + "\n" +
            accept + "\n" +
            contentMd5 + "\n" +
            contentType + "\n" +
            date + "\n" +
            canonicalizedHeaders + "\n" + canonicalizedResource;
    }

    private String getCanonicalizedHeaders(Map<String, String> headers) {
        List<String> headerKey = Lists.newArrayList();
        for (String s : headers.keySet()) {
            s = s.toLowerCase();
            if (s.startsWith("x-acs-")) {
                headerKey.add(s);
            }
        }
        Collections.sort(headerKey);
        String canonicalizedHeader = "";
        for (String s : headerKey) {
            String headerLine = s.trim() + ":" + headers.get(s).trim();
            if (canonicalizedHeader.length() == 0) {
                canonicalizedHeader = headerLine;
            } else {
                canonicalizedHeader = canonicalizedHeader + "\n" + headerLine;
            }
        }
        return canonicalizedHeader;
    }

    private String getCanonicalizedResouce(String pathName, String queryString) {
        if (Objects.isNull(queryString) || queryString.length() < 1) {
            return pathName;
        }
        List<String> queryList = Splitter.on("&").splitToList(queryString);
        Collections.sort(queryList);
        String sortedQueryString = Joiner.on("&").join(queryList);
        return pathName + "?" + sortedQueryString;
    }

    private String sign(String strToSign, String sk) throws Exception {
        SecretKey key = new SecretKeySpec(sk.getBytes("utf-8"), SignatureMethod.HMAC_SHA1);
        Mac hmacSha1 = Mac.getInstance("HmacSHA1");
        hmacSha1.init(key);
        return new String(new Base64().encode(hmacSha1.doFinal(strToSign.getBytes("utf-8"))),
            "utf-8");
    }
}


