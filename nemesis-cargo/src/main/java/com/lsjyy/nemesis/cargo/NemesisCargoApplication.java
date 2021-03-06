package com.lsjyy.nemesis.cargo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.lsjyy.nemesis.common","com.lsjyy.nemesis.cargo"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lsjyy.nemesis.cargo.dao")
//@EnableDistributedTransaction //分布式事务注解
public class NemesisCargoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NemesisCargoApplication.class, args);
    }

}
