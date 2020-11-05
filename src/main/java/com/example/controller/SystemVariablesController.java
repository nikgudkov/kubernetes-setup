package com.example.controller;


import com.example.dto.SystemMetricsDto;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/")
public class SystemVariablesController {

    @Value("${APP_SERVICE_NAME}")
    private String appName;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<String> getVariables() throws UnknownHostException {
        OperatingSystemMXBean systemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        SystemMetricsDto systemMetricsDto = new SystemMetricsDto(systemMXBean.getAvailableProcessors(), systemMXBean.getName(), systemMXBean.getArch(), systemMXBean.getSystemLoadAverage(),
                systemMXBean.getSystemCpuLoad(), systemMXBean.getProcessCpuLoad(), systemMXBean.getProcessCpuTime(), systemMXBean.getVersion());

        InetAddress ip = InetAddress.getLocalHost();
        return Mono.just(String.format("Service %s - %s, %s", appName, ip, systemMetricsDto));
    }

}
