package com.cfysu.aliyun;


import com.aliyun.retailbot20210224.Client;
import com.aliyun.retailbot20210224.models.AddSolutionRequest;
import com.aliyun.retailbot20210224.models.AddSolutionResponse;
import com.aliyun.retailbot20210224.models.ListSubscribedPackageKnowledgesRequest;
import com.aliyun.retailbot20210224.models.ListSubscribedPackageKnowledgesResponse;
import com.aliyun.retailbot20210224.models.ListSubscriptionByPackageRequest;
import com.aliyun.retailbot20210224.models.ListSubscriptionByPackageResponse;
import com.aliyun.retailbot20210224.models.QueryArtificialServicePolicyRequest;
import com.aliyun.retailbot20210224.models.QueryArtificialServicePolicyResponse;
import com.aliyun.retailbot20210224.models.RecognizeMessageForTestRequest;
import com.aliyun.retailbot20210224.models.RecognizeMessageRequest;
import com.aliyun.retailbot20210224.models.RecognizeMessageResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Author canglong
 * @Date 2022/1/4
 */
public class Main {
    public static void main(String[] args) throws Exception{
        String accountAccessAK = "XXXXXXXXXXXXXXXXXX";
        String accountAccessSK = "XXXXXXXXXXXXXXXXXX";
        // 协商提供以下POP信息
        String popRegion = "xxx";
        String popProduct = "xxx";
        String popDomain = "xxx";
        DefaultProfile.addEndpoint(popRegion, popProduct, popDomain);
        IClientProfile profile = DefaultProfile.getProfile(popRegion, accountAccessAK, accountAccessSK);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        //固定入参
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysProduct("xxx");
        commonRequest.setSysMethod(MethodType.GET);
        //根据API会有变化
        commonRequest.setSysAction("xxx");
        commonRequest.setSysVersion("2017-10-11");
        commonRequest.putQueryParameter("Utterance", "xxx");
        //机器人id
        commonRequest.putQueryParameter("InstanceId", "xxxxxxxxxxxx");
        CommonResponse commonResponse = client.getCommonResponse(commonRequest);
        System.out.println(commonResponse.getData());

        Config config = new Config();
        Client retailClient = new Client(config);
        ListSubscriptionByPackageRequest request = new ListSubscriptionByPackageRequest();
        ListSubscriptionByPackageResponse listSubscriptionByPackageResponse = retailClient.listSubscriptionByPackage(
            request);
        ListSubscribedPackageKnowledgesResponse listSubscribedPackageKnowledgesResponse
            = retailClient.listSubscribedPackageKnowledges(new ListSubscribedPackageKnowledgesRequest());

        AddSolutionResponse addSolutionResponse = retailClient.addSolution(new AddSolutionRequest());

        retailClient.recognizeMessageForTest(new RecognizeMessageForTestRequest());

        RecognizeMessageResponse recognizeMessageResponse = retailClient.recognizeMessage(
            new RecognizeMessageRequest());

        QueryArtificialServicePolicyRequest queryArtificialServicePolicyRequest = new QueryArtificialServicePolicyRequest();
        QueryArtificialServicePolicyResponse queryArtificialServicePolicyResponse
            = retailClient.queryArtificialServicePolicy(queryArtificialServicePolicyRequest);
    }

}
