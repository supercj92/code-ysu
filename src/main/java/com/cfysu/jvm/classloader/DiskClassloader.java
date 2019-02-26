package com.cfysu.jvm.classloader;

/**
 * @Author canglong
 * @Date 2018/11/28
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class DiskClassloader extends ClassLoader {

    private String mLibPath;

    public DiskClassloader(String path) {
        // TODO Auto-generated constructor stub
        mLibPath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // TODO Auto-generated method stub

        String fileName = getFileName(name);

        File file = new File(mLibPath,fileName);

        try {
            FileInputStream is = new FileInputStream(file);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = is.read()) != -1) {
                    bos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = bos.toByteArray();
            is.close();
            bos.close();

            return defineClass(name,data,0,data.length);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    //获取要加载 的class文件名
    private String getFileName(String name) {
        // TODO Auto-generated method stub
        int index = name.lastIndexOf('.');
        if(index == -1){
            return name+".class";
        }else{
            return name.substring(index+1)+".class";
        }
    }

    public static void main(String[] args) throws Exception{
        DiskClassloader diskClassloader = new DiskClassloader("/Users/chris/IdeaProjects/mine/code-ysu/src/main/java/com/cfysu/jvm/classloader");
        Class<?> testClass = diskClassloader.loadClass("com.cfysu.jvm.classloader.Test");
        Object instance = testClass.newInstance();
        Method method = testClass.getDeclaredMethod("say", null);
        method.invoke(instance, null);
        //这个class是由app加载的，并不是由diskClassloader加载的
        System.out.println(testClass.getClassLoader().toString());
        System.out.println(Test.class.getClassLoader().toString());
        System.out.println(Integer.class.getClassLoader());
        System.out.println(DiskClassloader.class.getClassLoader());
        System.out.println(DiskClassloader.class.getClassLoader().getParent());
        System.out.println(DiskClassloader.class.getClassLoader().getParent().getParent());
        if(instance instanceof Test){
            System.out.println("is test");
        }else {
            System.out.println("not test");
        }
    }

}
