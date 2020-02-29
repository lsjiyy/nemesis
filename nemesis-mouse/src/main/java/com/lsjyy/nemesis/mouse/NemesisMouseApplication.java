package com.lsjyy.nemesis.mouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lsjyy.nemesis.mouse.dao")
@SpringBootApplication(scanBasePackages = {"com.lsjyy.nemesis.common","com.lsjyy.nemesis.mouse"})
public class NemesisMouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(NemesisMouseApplication.class, args);
	}

}
