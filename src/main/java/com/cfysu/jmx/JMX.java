package com.cfysu.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

//启动后，使用Jconsole可以任意修改user的属性
public class JMX {
    public static void main(String[] args) throws MalformedObjectNameException, InstanceAlreadyExistsException,
            MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("jmxdemo:type=User");
        User user = new User();
        beanServer.registerMBean(user, objectName);
        String oldusername = null;
        String oldpassword = null;
        while (true) {
            if (oldusername != user.getUsername() || oldpassword != user.getPassword()) {
                System.out.println(user.getUsername() + "," + user.getPassword());
                oldusername = user.getUsername();
                oldpassword = user.getPassword();
            }
            Thread.sleep(1000);
        }
    }
}
