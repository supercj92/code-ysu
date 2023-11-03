package com.cfysu.lab.apache.velocity.prompt;

import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author canglong
 * @Date 2023/6/7
 */
@Slf4j
public class TemplateUtils {
    /**
     * 保留foreach中in前后的空格
     */
    private static final Pattern compiledPattern = Pattern.compile("(?<!in)[ \\t]+(?!in)");
    private static final Pattern carriagePattern = Pattern.compile("\n");
    private static final Pattern brPattern = Pattern.compile("<br>");
    private static final Pattern sPattern = Pattern.compile("<s>");

    /**
     * 去除模板中的空格和换行
     */
    public static String removeFormat(String template) {
        if (CommonSwitch.openDebugLog) {
            log.info("template origin  {}", template);
        }
        template = carriagePattern.matcher(template).replaceAll("");
        template = compiledPattern.matcher(template).replaceAll("");
        if (CommonSwitch.openDebugLog) {
            log.info("template after removeFormat {}", template);
        }
        return template;
    }

    /**
     * 替换模板中的空格和换行
     */
    public static String replaceFormat(String prompt) {
        if (CommonSwitch.openDebugLog) {
            log.info("prompt origin  {}", prompt);
        }
        prompt = brPattern.matcher(prompt).replaceAll("\n");
        prompt = sPattern.matcher(prompt).replaceAll(" ");
        if (CommonSwitch.openDebugLog) {
            log.info("prompt after replaceFormat {}", prompt);
        }
        return prompt;
    }
}
