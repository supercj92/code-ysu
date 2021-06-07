package com.cfysu.socket.protocol;

import java.io.Serializable;

import com.cfysu.io.SerialUtil;
import lombok.Data;

/**
 * @Author canglong
 * @Date 2021/6/4
 */
@Data
public class Protocol implements Serializable {

    public static final int TYPE_IN = 1;
    public static final int TYPE_OUT = 2;

    private int type;
    private String version;
    private int payload;


    public Protocol(){
        this.version = "1.1";
    }

}
