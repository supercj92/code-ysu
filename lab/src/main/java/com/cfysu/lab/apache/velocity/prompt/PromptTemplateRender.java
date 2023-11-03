package com.cfysu.lab.apache.velocity.prompt;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Author canglong
 * @Date 2023/5/23
 */
@Slf4j
@Component
public class PromptTemplateRender {

    @Autowired
    private VelocityEngine velocityEngine;

    public RenderedResult render(String template, JSONObject context) {
        Assert.notNull(template, "prompt template must not be null");
        Assert.notNull(context, "prompt context must not be null");
        template = TemplateUtils.removeFormat(template);
        RenderedResult renderedResult = doRender(template, context);
        renderedResult.prompt = TemplateUtils.replaceFormat(renderedResult.prompt);
        return renderedResult;
    }

    private RenderedResult doRender(String template, JSONObject context) {
        VelocityContext velocityContext = new VelocityContext();
        context.forEach(velocityContext::put);
        velocityContext.put("context", context);
        velocityContext.put("templateCodes", new ArrayList<>());
        List<String> missedVars = new ArrayList<>();
        velocityContext.put("missedVars", missedVars);
        velocityContext.put("velocityEngine", velocityEngine);
        String prompt;
        StringWriter stringWriter = new StringWriter();
        velocityEngine.evaluate(velocityContext, stringWriter, "PromptTemplateRender", template);
        prompt = stringWriter.toString();
        List<String> missedKeys = findMissedKeys(prompt, velocityContext.getKeys());
        missedVars.addAll(missedKeys);
        List<String> templateCodes = (List<String>)velocityContext.get("templateCodes");

        List<String> distinctTemplateCodes = templateCodes.stream().distinct().collect(Collectors.toList());
        return new RenderedResult(prompt, missedVars, distinctTemplateCodes);
    }

    private List<String> findMissedKeys(String prompt, String[] allVars) {
        List<String> missedKeys = new ArrayList<>();
        for (String var : allVars) {
            if (prompt.contains(var)) {
                missedKeys.add(var);
            }
        }
        return missedKeys;
    }

    @Data
    @AllArgsConstructor
    public static class RenderedResult {
        private String prompt;
        private List<String> missedKeys;
        private List<String> templateCodes;
    }
}
