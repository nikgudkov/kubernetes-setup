package com.example.dto;

import lombok.Value;

@Value
public class SystemMetricsDto {

    int availableProcessors;
    String getName;
    String getArch;
    double getSystemLoadAverage;
    double getSystemCpuLoad;
    double getProcessCpuLoad;
    long getProcessCpuTime;
    String getVersion;

}
