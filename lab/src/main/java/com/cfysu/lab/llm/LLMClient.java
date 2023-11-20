package com.cfysu.lab.llm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import reactor.core.publisher.Flux;

/**
 * @Author canglong
 * @Date 2023/7/6
 */
@Slf4j
@Component
public class LLMClient {

    private WebClient webClient = WebClient.create();

    public Flux<ServerSentEvent<String>> asyncCall(String query, String modelName) {
        return call(query, modelName, true);
    }

    public ServerSentEvent<String> syncCall(String query, String modelName) {
        return call(query, modelName, false).blockLast();
    }

    private Flux<ServerSentEvent<String>> call(String query, String modelName, boolean stream) {
        //QWenConversationParam qWenConversationParam = QWenConversationParam.builder().model(
        //    modelName).prompt(query).stream(stream).build();
        String body = "";//qWenConversationParam.buildMessageBody(Protocol.HTTP.getValue());
        log.info(body);

        RequestHeadersSpec<?> requestHeadersSpec = webClient.post()
            .uri("https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.TEXT_EVENT_STREAM)
            .header("Authorization", "xxx")
            .header("X-Accel-Buffering", "no")
            .bodyValue(body);

        ParameterizedTypeReference<ServerSentEvent<String>> type
            = new ParameterizedTypeReference<ServerSentEvent<String>>() {};

        return requestHeadersSpec
            .retrieve()
            .bodyToFlux(type);
    }
}
