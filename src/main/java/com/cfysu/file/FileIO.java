package com.cfysu.file;

import java.io.*;
import java.util.Scanner;

public class FileIO {
    public static void main(String[] args){

        FileIO fileIO = new FileIO();
        try {
            //fileIO.readFromConsoleToFile();
            fileIO.copyFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromConsoleToFile() throws IOException {
        Scanner scanner = new Scanner(System.in);
        File outFile = new File("D:/JavaFile" , "out.txt");
        if(!outFile.exists()){
            outFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(outFile);
        while (scanner.hasNext()){
            String consoleStr = scanner.next();
            fileWriter.write(consoleStr);
            fileWriter.flush();
            if("exit".equals(consoleStr)){
                scanner.close();
                fileWriter.close();
                return;
            }
        }
    }

    public void copyFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\longcangjian\\Desktop\\类目logs\\etms-contract-web.log.20170912-1505296766\\export\\Logs\\etms-contract-web\\etms-contract-web.log.20170912"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/JavaFile/test.log"));
        byte[] buffer = new byte[1024*1024];
        int count = 0;
        int num = 0;
        long startTime = System.currentTimeMillis();
        while ((count = fileInputStream.read(buffer)) != -1){
            fileOutputStream.write(buffer, 0, count);
            num++;
        }
        fileInputStream.close();
        fileOutputStream.close();
        long endTime = System.currentTimeMillis();
        System.out.println("循环次数:" + num);
        System.out.println("耗时:" + (endTime - startTime));
    }
}
