package com.cfysu.lab.apache.velocity.prompt;


import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.runtime.resource.util.StringResource;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

/**
 * @Author canglong
 * @Date 2023/5/29
 */
@Slf4j
public class DatabaseTemplateRepository implements StringResourceRepository {
    public String getEncoding() {
        // TODO Auto-generated method stub
        return "UTF-8";
    }

    public StringResource getStringResource(String templateCode) {
        if (CommonSwitch.openDebugLog){
            log.info("templateCode {}", templateCode);
        }
        //////todo test code
        //return new StringResource("be ref template, ${name}, ${xx}", this.getEncoding());
        if (templateCode.contains(".")) {
            return null;
        }
        try {
            String content = null;//PromptTemplateServiceWrapper.queryContentByTemplateCode(templateCode);
            content = TemplateUtils.removeFormat(content);
            return new StringResource(content, this.getEncoding());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void putStringResource(String arg0, String arg1, String arg2) {
        // TODO Auto-generated method stub
    }

    public void putStringResource(String arg0, String arg1) {
        // TODO Auto-generated method stub
    }

    public void removeStringResource(String arg0) {
        // TODO Auto-generated method stub
    }

    public void setEncoding(String arg0) {
        // TODO Auto-generated method stub
    }
}
