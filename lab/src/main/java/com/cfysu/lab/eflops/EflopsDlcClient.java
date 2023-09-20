package com.cfysu.lab.eflops;

/**
 * @Author canglong
 * @Date 2023/9/4
 */
import java.util.Map;
import java.util.UUID;

import com.aliyun.pai_dlc20201203.models.CreateJobRequest;
import com.aliyun.pai_dlc20201203.models.CreateJobResponseBody;
import com.aliyun.pai_dlc20201203.models.GetJobResponseBody;
import com.aliyun.tea.TeaModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author 筱井 [yuhua.zyh@alibaba-inc.com]
 * @since 2023-04-28
 */
public class EflopsDlcClient {
    private final RestTemplate template;

    private final String endPoint;

    private final String ak;

    private final String sk;

    private static final String JOBS = "/api/v1/jobs";

    public EflopsDlcClient(RestTemplate template, String ak, String sk, String endPoint) {
        this.template = template;
        this.endPoint = endPoint;
        this.ak = ak;
        this.sk = sk;
    }

    public CreateJobResponseBody createJob(CreateJobRequest createJobRequest) throws Exception {
        String path = JOBS;
        HttpEntity requestEntity = new HttpEntity(createJobRequest.toMap(), createHeader());
        ResponseEntity<Map> response = template.exchange(endPoint + path, HttpMethod.POST, requestEntity,
            Map.class);
        return TeaModel.toModel(response.getBody(), new CreateJobResponseBody());
    }

    public GetJobResponseBody queryJob(String jobId) throws Exception {
        String path = JOBS.concat("/").concat(jobId);
        HttpEntity requestEntity = new HttpEntity(createHeader());
        ResponseEntity<Map> response = template.exchange(endPoint + path, HttpMethod.GET, requestEntity,
            Map.class);
        return TeaModel.toModel(response.getBody(), new GetJobResponseBody());
    }

    private HttpHeaders createHeader() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("x-acs-signature-method", "HMAC-SHA1");
        requestHeaders.add("x-pai-product", "dlc");
        requestHeaders.add("x-acs-signature-nonce", UUID.randomUUID().toString().toLowerCase());
        requestHeaders.add("eflops-ak", ak);
        requestHeaders.add("eflops-sk", sk);
        return requestHeaders;
    }

}

