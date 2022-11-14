package com.cfysu.lab.es;

import java.io.IOException;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

/**
 * @Author canglong
 * @Date 2022/8/5
 */
@Slf4j
public class ESMain {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                new HttpHost("localhost", 9200, "http")));

        try {
            get(client);
        } catch (Exception e) {
            log.error("", e);
        } finally {
            client.close();
        }
    }

    public static void index(RestHighLevelClient client) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "kimchy");
            builder.field("message", "trying out Elasticsearch");
        }
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest("twitter").type("doc")
            .id("1").source(builder);
        IndexResponse index = client.index(indexRequest);
    }

    public static void get(RestHighLevelClient client) throws Exception {
        GetRequest getRequest = new GetRequest(
            "twitter",
            "doc",
            "1");
        String[] includes = new String[] {"message", "*date"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext =
            new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);

        GetResponse getResponse = client.get(getRequest);
    }

}
