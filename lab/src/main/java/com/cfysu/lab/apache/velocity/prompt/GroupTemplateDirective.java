package com.cfysu.lab.apache.velocity.prompt;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

/**
 * @Author canglong
 * @Date 2023/5/23
 * 指定类目，从该类目下随机获取一个模板
 */
@Slf4j
public class GroupTemplateDirective extends Directive {

    private RefTemplateDirective refTemplateDirective = new RefTemplateDirective();

    @Override
    public String getName() {
        return "groupTemplate";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
        throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        //获取第一个入参
        SimpleNode sn_data = (SimpleNode)node.jjtGetChild(0);
        Object param = sn_data.value(context);
        String templateCategory = (String)param;
        if (CommonSwitch.openDebugLog) {
            log.info("template category {}", templateCategory);
        }
        if (param == null) {
            log.info("GroupTemplateDirective param is null");
            return false;
        }

        String[] categories = StringUtils.split(templateCategory, "||");
        String templateCode = null;//PromptTemplateServiceWrapper.getTemplateCodeRandomly(Arrays.asList(categories));
        if (CommonSwitch.openDebugLog) {
            log.info("from templateCategory {}, selected templateCode {}", templateCategory, templateCode);
        }
        refTemplateDirective.render(context, writer, node, templateCode);
        return true;
    }
}
