package com.cfysu.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author canglong
 * @Date 2021/8/6
 */
public class FileUtil {
    public static void writeBytesToFile(byte[] bytes, String path) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
        byte[] buffer = new byte[8 * 1024];
        int read;

        while ((read = byteArrayInputStream.read(buffer)) != -1){
            //直接写buffer存在bug，如果buffer不满，则会写入上一次循环遗留的数据
            //fileOutputStream.write(buffer);
            fileOutputStream.write(buffer, 0, read);
        }
        byteArrayInputStream.close();

        fileOutputStream.flush();
        fileOutputStream.close();
        System.out.println("writeBytesToFile success");
    }

    public static byte[] readBytesFromFile(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[8 * 1024];

        int read;
        while ((read = fileInputStream.read(buffer)) != -1){
            byteArrayOutputStream.write(buffer, 0, read);
        }
        fileInputStream.close();
        byteArrayOutputStream.close();

        System.out.println("readBytesFromFile success");
        return byteArrayOutputStream.toByteArray();
    }
}
