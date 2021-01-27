package com.cfysu.bean;

import java.util.List;

import com.cfysu.io.CarDO1;
import com.cfysu.util.Array;
import lombok.Data;
import org.springframework.context.annotation.DependsOn;

/**
 * @Author canglong
 * @Date 2019/1/17
 */
@Data
//@DependsOn("dd")
@Array("ss")
public class Hero {

    private String firstName;
    private String lastName;
    private List<CarDO1> carDO1s;
}
