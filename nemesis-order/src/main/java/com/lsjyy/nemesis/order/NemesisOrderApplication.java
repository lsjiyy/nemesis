package com.lsjyy.nemesis.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.lsjyy.nemesis")
@MapperScan(basePackages = "com.lsjyy.nemesis.order.dao")
public class NemesisOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(NemesisOrderApplication.class, args);
    }
}


