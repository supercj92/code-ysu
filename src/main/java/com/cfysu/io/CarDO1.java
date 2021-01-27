package com.cfysu.io;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author canglong
 * @Date 2020/8/7
 */
@Data
@AllArgsConstructor
public class CarDO1 implements Serializable {

    private static final long serialVersionUID = 7861647066142668592L;

    private String brand;
}
