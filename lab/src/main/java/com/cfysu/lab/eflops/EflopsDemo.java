package com.cfysu.lab.eflops;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.aliyun.pai_dlc20201203.models.CreateJobRequest;
import com.aliyun.pai_dlc20201203.models.CreateJobResponseBody;
import com.aliyun.pai_dlc20201203.models.JobSpec;
import com.aliyun.pai_dlc20201203.models.ResourceConfig;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * @author 筱井 [yuhua.zyh@alibaba-inc.com]
 * @since 2023-03-30
 */
public class EflopsDemo {
    static RestTemplate template;
    static String ak = "E-yuhua.zyh-146671";
    static String sk = "";
    static String endpoint = "http://pai-proxy.xxxx.alicontainer.com:80";

    static {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(50000);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(converter);

        EflopsHttpRequestInterceptor eflopsHttpRequestInterceptor = new EflopsHttpRequestInterceptor();

        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.setInterceptors(Lists.newArrayList(eflopsHttpRequestInterceptor));
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        template = restTemplate;
    }

    private static void run() throws Exception {
        EflopsDlcClient eflopsDlcClient = new EflopsDlcClient(template, ak, sk, endpoint);

        CreateJobRequest createJobRequest = new CreateJobRequest();
        createJobRequest.setDisplayName("first-from-api");
        JobSpec jobSpec = new JobSpec();
        jobSpec.setImage("master0:5000/eflops/m6-finetune:1.9.2");
        jobSpec.setPodCount(1L);
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.setCPU("100");
        resourceConfig.setGPU("8");
        resourceConfig.setGPUType("nvidia_a100-sxm4-80gb");
        resourceConfig.setMemory("600Gi");
        resourceConfig.setSharedMemory("20Gi");
        jobSpec.setResourceConfig(resourceConfig);
        jobSpec.setType("Worker");
        createJobRequest.setJobSpecs(Lists.newArrayList(jobSpec));
        createJobRequest.setUserCommand(
            "PYTHONPATH=/app torchrun --nproc_per_node 8 /app/examples/finetune_text_generation.py   --model "
                + "/mnt/shared/public/models/13b  --dataset_name /mnt/shared/public/demo-data/artifact     --work_dir"
                + " /mnt/shared/public/models/test13b    --epochs 20     --batch_size 16     --learning_rate 1.6e-5  "
                + "--world_size 8  --tensor_model_parallel_size 1");
        createJobRequest.setWorkspaceId("ws126a490s9v0346");
        createJobRequest.setJobType("PyTorchJob");
        CreateJobResponseBody createJobResponseBody = eflopsDlcClient.createJob(createJobRequest);
        System.out.println(createJobResponseBody.jobId);
    }

    public static void main(String[] args) throws Exception {
        run();
    }
}

