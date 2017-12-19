package com.cfysu.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * jdk1.4引入nio
 */
public class NioDemo {
    public static void main(String[] args) throws IOException {
        File fromFile = new File("D:\\saveImage.jpg");
        File toFile = new File("D:\\saveImage3.jpg");
        copyFileByNio2(fromFile, toFile);
        System.out.println("文件复制完成");
    }

    public static void copyFileByNio(File fromFile,File toFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fromFile);
        FileOutputStream fileOutputStream = new FileOutputStream(toFile);

        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, fromFile.length());

        outChannel.write(buffer);

        buffer.clear();
    }

    public static void copyFileByNio2(File fromFile, File toFile) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(fromFile);
        FileOutputStream fileOutputStream = new FileOutputStream(toFile);

        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int count = 0;
        while (inChannel.read(buffer) != -1){
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
            count++;
        }
        System.out.println(count);
    }
}
