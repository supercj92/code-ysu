package com.cfysu.midware.rpc.simpleRPC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RPCExporter {

    static Executor threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exportSevice(String hostName , int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(hostName, port));
        while (true){
            threadPool.execute(new SocketHandler(serverSocket.accept()));
        }
    }

    static class SocketHandler implements Runnable{

        private Socket socket;

        SocketHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                String interfaceName = objectInputStream.readUTF();
                Class<?> sevice = Class.forName(interfaceName);
                String methodName = objectInputStream.readUTF();
                Class<?>[] parmTypes = (Class<?>[]) objectInputStream.readObject();
                Object[] args = (Object[])objectInputStream.readObject();
                Method method = sevice.getMethod(methodName, parmTypes);
                Object result = method.invoke(sevice.newInstance(), args);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
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
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

