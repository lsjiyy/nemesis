package com.lsjyy.nemesis.cargo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.lsjyy.nemesis")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lsjyy.nemesis.cargo.dao")
public class NemesisCargoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NemesisCargoApplication.class, args);
    }

    /**
     *  目的
     *  	1. 货品列表缓存在redis中,每两小时刷新一次,货品每次修改加入redis缓存,减轻对mysql的访问量
     *  	2. 秒杀也将做
     */
}
