package com.cfysu.jmx;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//启动后，使用Jconsole可以任意修改user的属性
public class JMXServer {
    public static void main(String[] args) throws MalformedObjectNameException, InstanceAlreadyExistsException,
            MBeanRegistrationException, NotCompliantMBeanException, InterruptedException, IOException {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("jmxdemo:type=User");
        User user = new User("cj", "1234");
        beanServer.registerMBean(user, objectName);
//        String oldusername = null;
//        String oldpassword = null;
//        while (true) {
//            if (oldusername != user.getUsername() || oldpassword != user.getPassword()) {
//                System.out.println(user.getUsername() + "," + user.getPassword());
//                oldusername = user.getUsername();
//                oldpassword = user.getPassword();
//            }
//            Thread.sleep(1000);
//        }

        //这句话非常重要，不能缺少！注册一个端口，绑定url后，客户端就可以使用rmi通过url方式来连接JMXConnectorServer
        Registry registry = LocateRegistry.createRegistry(1399);

        //构造JMXServiceURL
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1399/jmxrmi");
        //创建JMXConnectorServer
        JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, beanServer);
        //启动
        cs.start();
    }
}
