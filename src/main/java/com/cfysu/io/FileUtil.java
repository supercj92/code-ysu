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
    public static void writeBytesToFile(byte[] bytes, File file) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];

        while (byteArrayInputStream.read(buffer) != -1){
            fileOutputStream.write(buffer);
        }
        byteArrayInputStream.close();
        fileOutputStream.close();
        System.out.println("writeBytesToFile success");
    }

    public static byte[] readBytesFromFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        while (fileInputStream.read(buffer) != -1){
            byteArrayOutputStream.write(buffer);
        }
        fileInputStream.close();
        byteArrayOutputStream.close();

        System.out.println("readBytesFromFile success");
        return byteArrayOutputStream.toByteArray();
    }
}
