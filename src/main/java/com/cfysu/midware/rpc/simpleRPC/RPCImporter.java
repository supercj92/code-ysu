package com.cfysu.midware.rpc.simpleRPC;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;


public class RPCImporter<S> {
    public S importService(final Class<?> serviceName, final InetSocketAddress inetSocketAddress){
        return (S) Proxy.newProxyInstance(serviceName.getClassLoader(), new Class<?>[]{serviceName.getInterfaces()[0]}, new ServiceInvocationHandler(inetSocketAddress, serviceName));
    }

    class ServiceInvocationHandler implements InvocationHandler{

        private InetSocketAddress inetSocketAddress;

        private Class<?> serviceName;

        ServiceInvocationHandler(InetSocketAddress inetSocketAddress, Class<?> serviceName){
            this.inetSocketAddress = inetSocketAddress;
            this.serviceName = serviceName;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            //1.建立tcp连接
            Socket socket = null;
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;
            Object object = null;
            try {
                socket = new Socket();
                socket.connect(inetSocketAddress);
                //2.发送接口入参

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeUTF(serviceName.getName());
                objectOutputStream.writeUTF(method.getName());
                objectOutputStream.writeObject(method.getParameterTypes());
                objectOutputStream.writeObject(args);
                //3.读取接口结果
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                object = objectInputStream.readObject();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(objectInputStream != null){
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(objectOutputStream != null){
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return object;
        }
    }
}
