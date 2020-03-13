package com.lsjyy.nemesis.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableAutoConfiguration
@MapperScan("com.lsjyy.nemesis.system.dao")
public class NemesisSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(NemesisSystemApplication.class, args);
    }


}
