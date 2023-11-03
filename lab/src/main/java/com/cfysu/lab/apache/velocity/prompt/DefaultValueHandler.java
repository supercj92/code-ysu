package com.cfysu.lab.apache.velocity.prompt;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.apache.velocity.context.Context;

/**
 * @Author canglong
 * @Date 2023/6/5
 */
public class DefaultValueHandler implements ReferenceInsertionEventHandler {
    @Override
    public Object referenceInsert(Context context, String reference, Object value) {
        if (value == null){
            value = "<无>";
        }
        return value;
    }
}
