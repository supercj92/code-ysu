package com.cfysu.spring.context.constructor;

import java.util.List;

/**
 * @Author canglong
 * @Date 2021/3/10
 */
public class ComponentHandler {

    private List<Nlu> nlus;

    /**
     * spring可以根据泛型区分要注入哪个bean
     * 在这个例子中，注入了Repository<Nlu>类型的实例，而不是Repository<Nlg>类型的
     */
    private Repository<Nlu> repository;

    /**
     * 构造方法可以直接注入
     * @param nlus
     */
    public ComponentHandler(List<Nlu> nlus, Repository<Nlu> repository){
        this.nlus = nlus;
        this.repository = repository;
    }


}
