package com.cfysu.agent;

import java.lang.instrument.Instrumentation;

/**
 * @Author canglong
 * @Date 2020/6/5
 * 启动jvm时，可以通过指定参数来启动agent
 */
public class Attach {
    public static void agentmain(String args, Instrumentation instrumentation){
        System.out.println("agentmain, attach success, args:" + args);
    }

    public static void premain(String agentArgs, Instrumentation inst){
        //-javaagent:/Users/chris/IdeaProjects/mine/code-ysu/agent/target/code-ysu-agent.jar=testMsg
        System.out.println("premain, agentArgs : " + agentArgs);
    }
}
