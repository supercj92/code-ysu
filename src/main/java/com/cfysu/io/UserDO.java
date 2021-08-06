package com.cfysu.io;

import java.io.Serializable;

import lombok.Data;

/**
 * @Author canglong
 * @Date 2020/8/6
 */
@Data
public class UserDO /*implements Serializable */{
    /**
     * 当序列化后的数据跟class中的版本号不一致时，反序列化会抛异常
     * serialVersionUID为了标识唯一class版本
     */
    //private static final long serialVersionUID = 2L;
    private String name;
    private Integer age;
    private String height;

    public UserDO(){}

    //private String carDO;

    //private CarDO1 carDO;
}
