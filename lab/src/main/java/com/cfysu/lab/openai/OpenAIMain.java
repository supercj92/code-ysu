package com.cfysu.lab.openai;

import java.net.ProxySelector;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

/**
 * @Author canglong
 * @Date 2023/3/23
 */
public class OpenAIMain {

    public static void main(String[] args) {
        OpenAiService service = new OpenAiService("");
        ProxySelector aDefault = ProxySelector.getDefault();
        CompletionRequest completionRequest = CompletionRequest.builder()
            .prompt("介绍下openai")
            .model("text-davinci-003")
            .echo(false).maxTokens(100)
            .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);

    }
}
