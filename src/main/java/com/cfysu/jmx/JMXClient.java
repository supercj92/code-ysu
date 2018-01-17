package com.cfysu.jmx;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class JMXClient {
    public static void main(String[] args) throws IOException, MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException {
        //connect JMX
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1399/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url,null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        ObjectName mbeanName = new ObjectName("jmxdemo:type=User");

        //invoke
        UserMBean userMBean = MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, UserMBean.class, false);
        System.out.println("invoke before:" + userMBean.getDes());

        //process attribute
        mbsc.setAttribute(mbeanName, new Attribute("Username", "cj110")); //set value
        System.out.println("Username = " + mbsc.getAttribute(mbeanName, "Username")); //get value

        System.out.println("invoke after:" + userMBean.getDes());

        jmxc.close();
    }
}
