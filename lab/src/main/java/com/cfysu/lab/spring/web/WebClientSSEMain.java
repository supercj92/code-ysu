package com.cfysu.lab.spring.web;

import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @Author canglong
 * @Date 2023/6/25
 */
public class WebClientSSEMain {
    Logger logger = LoggerFactory.getLogger(WebClientSSEMain.class);

    @Test
    public void consumeServerSentEvent() {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        //String url = "http://localhost:4443/sse";
        String url
            = "https://pre-alime-llm-loop.alibaba-inc.com/chat/qianwen?query=介绍下杭州，不少于800字";

        WebClient client = WebClient.create(url);
        //HttpClient httpClient = HttpClient.create()
        //    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
        //    .responseTimeout(Duration.ofMillis(5000))
        //    .doOnConnected(conn ->
        //        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
        //            .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
        //
        //WebClient client = WebClient.builder().baseUrl(url)
        //    .clientConnector(new ReactorClientHttpConnector(httpClient))
        //    .build();

        ParameterizedTypeReference<ServerSentEvent<String>> type
            = new ParameterizedTypeReference<ServerSentEvent<String>>() {};

        Flux<ServerSentEvent<String>> eventStream = client.get()
            //.uri("/stream-sse")
            .retrieve()
            .bodyToFlux(type);

        eventStream.subscribe(
            content -> logger.info("Time: {} - event: name[{}], id [{}], content[{}] ",
                LocalTime.now(), content.event(), content.id(), content.data()),
            error -> logger.error("Error receiving SSE: {}", error),
            () -> {
                logger.info("Completed!!!");
                countDownLatch.countDown();
            });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
