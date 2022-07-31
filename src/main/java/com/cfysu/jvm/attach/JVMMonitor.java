package com.cfysu.jvm.attach;

import java.io.IOException;

//import com.sun.tools.attach.AgentInitializationException;
//import com.sun.tools.attach.AgentLoadException;
//import com.sun.tools.attach.AttachNotSupportedException;
//import com.sun.tools.attach.VirtualMachine;
//
///**
// * @Author canglong
// * @Date 2020/6/5
// * 动态的attach到已存在的jvm进程上
// */
//public class JVMMonitor {
//    public static void main(String[] args)
//        throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException {
//        VirtualMachine virtualMachine = VirtualMachine.attach("51914");
//        virtualMachine.loadAgent("/Users/chris/IdeaProjects/mine/code-ysu/agent/target/code-ysu-agent.jar");
//        virtualMachine.detach();
//    }
//}
