package com.cfysu.cli;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.cfysu.cli.FileUtils.CSVFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @Author canglong
 * @Date 2022/8/10
 */
@ShellComponent
public class ParseEASLog {

    @ShellMethod("解析eas服务日志")
    public String parseEAS(String originalPath) throws IOException {
        CSVFile csvFile = null;
        try {
            FileReader fileReader = new FileReader(originalPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            List<EASServiceInfo> easServiceInfos = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                easServiceInfos.add(parse(line));
            }
            easServiceInfos = easServiceInfos.stream().sorted(
                Comparator.comparingInt(EASServiceInfo::getInstanceNum).reversed()).collect(Collectors.toList());

            csvFile = FileUtils.openCSVFile(originalPath);

            for (EASServiceInfo easServiceInfo : easServiceInfos) {
                csvFile.write(easServiceInfo.toCSVLine());
            }

            long preCount = easServiceInfos.stream().filter(
                    easServiceInfo -> StringUtils.trim(easServiceInfo.serviceName).endsWith("_pre"))
                .count();
            System.out.println(preCount);
        } finally {
            if (csvFile != null) {
                csvFile.close();
            }
        }
        return csvFile.getFile().getAbsolutePath();
    }

    private EASServiceInfo parse(String line) {
        String easServiceName = line.substring(2, 48);
        String instanceNum = line.substring(49, 59);
        String cpuNum = line.substring(60, 65);
        String memory = line.substring(66, 74);
        return new EASServiceInfo(easServiceName, instanceNum, cpuNum,
            memory);
    }

    @Data
    private class EASServiceInfo {
        private String serviceName;
        private byte instanceNum;
        private Handware hardware;

        public EASServiceInfo(String serviceName, String instanceNum, String cpuNum, String memory) {
            this.serviceName = serviceName;
            this.instanceNum = Byte.parseByte(StringUtils.trim(instanceNum));
            this.hardware = new Handware(Byte.parseByte(StringUtils.trim(cpuNum)), memory);
        }

        public String toCSVLine() {
            Object[] objects = new Object[4];
            objects[0] = serviceName;
            objects[1] = instanceNum;
            objects[2] = hardware.getCpuNum();
            objects[3] = hardware.getMemory();

            return StringUtils.join(objects, ",");
        }
    }

    @Data
    @AllArgsConstructor
    private class Handware {
        private byte cpuNum;
        //单位M
        private String memory;
    }

}
