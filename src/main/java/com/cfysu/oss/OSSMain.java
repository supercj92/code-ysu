package com.cfysu.oss;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2021/11/5
 */
public class OSSMain {

    private OSS ossClient;

    private String accessKeyId = "xxxx";
    private String accessKeySecret = "xxx";
    private String endPoint = "oss-cn-hangzhou.aliyuncs.com";

    private String bucketName = "aiboost-annotation-daily";

    String objKey = "exampledir/10025911387_alpha_1.jpg";

    private long ONE_MINUTE_IN_MILLISECOND = 60 * 1000;

    @Before
    public void initOSSClient() {
        // 创建OSSClient实例。
        ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
    }

    @Test
    public void testListBuckets() {
        // 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }
    }

    @Test
    public void testUpload() {
        // 创建PutObjectRequest对象。
        // 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objKey,
            new File("/Users/chris/Downloads/10025911387_alpha_1.jpg"));
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        Date expireTime = new Date(System.currentTimeMillis() + ONE_MINUTE_IN_MILLISECOND);
        URL url = ossClient.generatePresignedUrl(bucketName, objKey, expireTime);
        System.out.println(url.toString());
    }

    @Test
    public void testDownload() {
        // 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
        // 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
        ossClient.getObject(new GetObjectRequest(bucketName, objKey),
            new File("/Users/chris/Downloads/test_download.jpg"));
    }

    @Test
    public void testListFiles(){
        // 列举文件。如果不设置keyPrefix，则列举存储空间下的所有文件。如果设置keyPrefix，则列举包含指定前缀的文件。
        ObjectListing objectListing = ossClient.listObjects(bucketName, "exampledir");
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            System.out.println("\t" + s.getKey());
        }
    }

    @After
    public void closeOSSClient() {
        ossClient.shutdown();
    }
}
