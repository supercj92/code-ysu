package com.cfysu.cli;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author canglong
 * @Date 2022/8/10
 */
public class FileUtils {
    public static CSVFile openCSVFile(String logPath) throws IOException {
        int fileNameStart = logPath.lastIndexOf('/');
        int fileNameEnd = logPath.lastIndexOf('.');
        String path = logPath.substring(0, fileNameStart + 1);
        String fileName = logPath.substring(fileNameStart + 1, fileNameEnd);
        File file = new File(path + fileName + ".csv");
        FileWriter fileWriter = new FileWriter(file);
        return new CSVFile(file, new BufferedWriter(fileWriter));
    }

    @Data
    @AllArgsConstructor
    public static class CSVFile{
        private File file;
        private BufferedWriter bufferedWriter;

        public void write(String line) throws IOException {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        public void close() throws IOException {
            bufferedWriter.close();
        }
    }
}
