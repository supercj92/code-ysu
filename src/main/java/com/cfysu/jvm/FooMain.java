package com.cfysu.jvm;

/**
 * @Author canglong
 * @Date 2022/7/21
 */
public class FooMain {
    //-Des.path.home=/Users/chris/eshome -Des.path.conf=/Users/chris/eshome/config -Xms1g -Xmx1g -Dlog4j2.disable.jmx=true -Djava.security.policy=/Users/chris/eshome/config/elasticsearch.policy

    public static void main(String[] args) {
        System.out.println(System.getProperty("es.path.home"));
        System.out.println("--aaaa--");
    }
}
