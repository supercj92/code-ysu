package com.cfysu.lab.apache.velocity.prompt;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTDirective;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

/**
 * @Author canglong
 * @Date 2023/5/22
 * 引用指定模板
 */
@Slf4j
public class RefTemplateDirective extends Directive {

    public static final String REF_TEMPLATE = "refTemplate";

    @Override
    public String getName() {
        return REF_TEMPLATE;
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
        String templateCode = (String)param;
        if (CommonSwitch.openDebugLog) {
            log.info("templateCode {}", templateCode);
        }
        if (param == null) {
            log.info("RefTemplateDirective param is null");
            return false;
        }
        render(context, writer, node, templateCode);
        return true;
    }

    public void render(InternalContextAdapter context, Writer writer, Node node, String templateCode)
        throws IOException {
        List<String> templateCodes = (List<String>)context.get("templateCodes");
        //System.out.println(JSON.toJSONString(templateCodes));
        templateCodes.add(templateCode);

        /*
         * Add the template name to the macro libraries list
         */
        List<Template> macroLibraries = context.getMacroLibraries();

        /*
         * if macroLibraries are not set create a new one
         */
        if (macroLibraries == null) {
            macroLibraries = new ArrayList<>();
        }

        context.setMacroLibraries(macroLibraries);

        VelocityEngine velocityEngine = (VelocityEngine)context.get("velocityEngine");
        Template t = velocityEngine.getTemplate(templateCode);
        /* instead of adding the name of the template, add the Template reference */
        context.pushCurrentTemplateName(templateCode);
        macroLibraries.add(t);

        SimpleNode simpleNode = ((SimpleNode)t.getData());
        VarCheckResult varCheckResult = allVarMiss(simpleNode, context);
        boolean allVarMiss = varCheckResult.allVarMiss;
        List<String> missedVars = varCheckResult.missedVars;
        List<String> missedVarsContext = (List<String>)context.get("missedVars");
        missedVarsContext.addAll(missedVars);

        try {
            //当待引用的模板所需的变量都缺失时，则弃用此模板
            if (!allVarMiss) {
                writer.write(((ASTDirective)node).getPrefix());
                ((SimpleNode)t.getData()).render(context, writer);
                writer.write(((ASTDirective)node).getPostfix());
            }
        } finally {
            context.popCurrentTemplateName();
        }
    }

    public VarCheckResult allVarMiss(SimpleNode simpleNode, InternalContextAdapter context) {
        int childCount = simpleNode.jjtGetNumChildren();

        int varCount = 0;
        int missCount = 0;
        List<String> missedVars = new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            Node node = simpleNode.jjtGetChild(i);
            if (node instanceof ASTReference) {
                ASTReference astReference = (ASTReference)node;
                varCount++;
                Object value = astReference.execute(null, context);
                if (value == null) {
                    missCount++;
                    missedVars.add(astReference.getRootString());
                }
            } else if (node instanceof ASTDirective) {
                ASTDirective astDirective = (ASTDirective)node;
                String directiveName = astDirective.getDirectiveName();
                if ("foreach".equals(directiveName)) {
                    varCount++;
                    Node iterableNode = node.jjtGetChild(2);
                    Object iterable = iterableNode.value(context);
                    if (iterable == null) {
                        missCount++;
                        missedVars.add(((ASTReference)iterableNode).getRootString());
                    }
                }
            }
        }
        if (CommonSwitch.openDebugLog) {
            log.info("{} has {} vars, {} of them miss", simpleNode.getTemplate().getName(), varCount, missCount);
        }
        //如果存在变量，并且全部变量缺失，才返回true
        return new VarCheckResult(missedVars, (varCount > 0) && (varCount == missCount));
    }

    @AllArgsConstructor
    public class VarCheckResult {
        private List<String> missedVars;
        private boolean allVarMiss;
    }
}
