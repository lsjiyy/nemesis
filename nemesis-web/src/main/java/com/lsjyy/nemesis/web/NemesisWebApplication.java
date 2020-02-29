package com.lsjyy.nemesis.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Feign 采用的是基于接口的注解
 * Feign 整合了ribbon，具有负载均衡的能力
 * 整合了Hystrix，具有熔断的能力
 *
 *
 * scanBasePackages 同时扫描自身类和引用模块儿类才能保证引用模块儿的类注入成功，
 * 可以scanBasePackages = {"com.lsjyy.nemesis.common.redis", "com.lsjyy.nemesis.common.redis"})多模块儿扫描
 *
 */


@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients  //开启feign功能
@SpringBootApplication(scanBasePackages = {"com.lsjyy.nemesis.common","com.lsjyy.nemesis.web"})
@EnableAutoConfiguration
public class NemesisWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(NemesisWebApplication.class, args);
	}

}
