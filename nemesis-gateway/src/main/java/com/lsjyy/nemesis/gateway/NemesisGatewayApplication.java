package com.lsjyy.nemesis.gateway;


import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.lsjyy.nemesis.gateway", "com.lsjyy.nemesis.common"})
@RestController
public class NemesisGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NemesisGatewayApplication.class, args);
    }

    /**
     * 连接不上服务调用fallback
     *
     * @return
     */
    @RequestMapping("/fallback")
    public AjaxResult fallback() {
        return AjaxResult.error("暂时无法访问,请联系管理员");
    }


    /**
     * 根据uri限流
     *
     * @return
     */
    @Bean
    public KeyResolver uriKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }
}
