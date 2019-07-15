package com.cfysu.spring.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author canglong
 * @Date 2019/7/15
 */
@Component
public class ProcessorFactory {

    private static Map<String, Processor> biz2ProcessorMap = new HashMap<>();

    public Processor getProcessor(String bizCode){
        return biz2ProcessorMap.get(bizCode);
    }

    @Autowired
    public void initFactory(Set<Processor> processorSet){
        for(Processor processor : processorSet){
            biz2ProcessorMap.put(processor.getType(), processor);
        }
        //processorSet.forEach(processor -> biz2ProcessorMap.put(processor.getType(), processor));
    }
}
