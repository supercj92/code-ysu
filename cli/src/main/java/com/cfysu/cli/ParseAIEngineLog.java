package com.cfysu.cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ch.qos.logback.core.util.FileUtil;
import com.cfysu.cli.FileUtils.CSVFile;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * @Author canglong
 * @Date 2022/8/1
 */
@ShellComponent
public class ParseAIEngineLog {

    @ShellMethod("解析日志并生成csv")
    public String parse(String logPath, String prod) throws IOException {
        System.out.println("prod:" + prod);
        FileReader fileReader = new FileReader(logPath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<ECI> eciList = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            eciList.add(parseLine(line, Boolean.parseBoolean(prod)));
        }

        return toCSV(eciList, logPath);
    }

    public class ECI {
        private String app;
        private String ip;

        private String host;

        public ECI(String app, String ip, String host) {
            this.app = StringUtils.trimAllWhitespace(app);
            this.ip = StringUtils.trimAllWhitespace(ip);
            this.host = StringUtils.trimAllWhitespace(host);
        }

        public String getApp() {
            return app;
        }

        public String getIp() {
            return ip;
        }

        public String getHost() {
            return host;
        }

        public String toCSVLine() {
            return String.format("%s,%s,%s", app, host, ip);
        }
    }

    private ECI parseLine(String line, boolean prod) {
        String appName = line.substring(0, 25);
        String ip = prod ? line.substring(78, 92) : line.substring(87, 101);
        String host = prod ? line.substring(25, 78) : line.substring(25, 87);
        return new ECI(appName, ip, host);
    }

    private String toCSV(List<ECI> eciList, String logPath) throws IOException {


        CSVFile csvFile = FileUtils.openCSVFile(logPath);
        BufferedWriter bufferedWriter = csvFile.getBufferedWriter();
        for (ECI eci : eciList) {
            bufferedWriter.write(eci.toCSVLine());
            bufferedWriter.newLine();
        }

        //select *, count(1) as total from table grouping by app,host order by total
        Map<String, Map<String, List<ECI>>> groupByAppHost = eciList.stream().collect(
            Collectors.groupingBy(ECI::getApp, Collectors.groupingBy(ECI::getHost)));
        //groupByAppHost.entrySet().stream().sorted(
        //    Comparator.comparing((Entry<String, Map<String, List<ECI>>> entry) -> entry.getValue().size()).reversed());

        Map<String, List<ECI>> collect = eciList.stream().collect(Collectors.groupingBy(ECI::getApp));
        String summary = collect.entrySet().stream().sorted(
                Comparator.comparingInt((Entry<String, List<ECI>> entry) -> entry.getValue().size()).reversed()).map(
                entry -> entry.getKey() + ":" + entry.getValue().size())
            .collect(Collectors.joining("\n"));
        System.out.println(summary);
        bufferedWriter.flush();

        //ReflectionUtils.doWithFields();

        return csvFile.getFile().getAbsolutePath();
    }
}
