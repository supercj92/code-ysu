package com.cfysu.spring.context.constructor;

/**
 * @Author canglong
 * @Date 2021/3/10
 */
public class Repository<T extends Component> {

    private String name;

    public Repository(String name){
        this.name = name;
    }
}
