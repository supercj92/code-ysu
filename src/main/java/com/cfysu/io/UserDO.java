package com.cfysu.io;

import java.io.Serializable;

import lombok.Data;

/**
 * @Author canglong
 * @Date 2020/8/6
 */
@Data
public class UserDO implements Serializable {
    private static final long serialVersionUID = -398376340642725009L;
    private String name;
    private Integer age;

    public UserDO(){}

    //private String carDO;

    //private CarDO1 carDO;
}
