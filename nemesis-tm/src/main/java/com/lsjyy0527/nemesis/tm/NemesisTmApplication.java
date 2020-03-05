package com.lsjyy0527.nemesis.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//分布式事务中心
@EnableTransactionManagerServer
@SpringBootApplication
@EnableDiscoveryClient
public class NemesisTmApplication {

	public static void main(String[] args) {
		SpringApplication.run(NemesisTmApplication.class, args);
	}

}
