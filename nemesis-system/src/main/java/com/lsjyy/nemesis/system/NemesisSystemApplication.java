package com.lsjyy.nemesis.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.system.pojo.ServiceInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.lsjyy.nemesis.system", "com.lsjyy.nemesis.common"})
@EnableAutoConfiguration
@MapperScan("com.lsjyy.nemesis.system.dao")
@EnableFeignClients
@RestController
public class NemesisSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(NemesisSystemApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(NemesisSystemApplication.class);

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test")
    public AjaxResult test() {
        return AjaxResult.success("成功了");
    }
}
