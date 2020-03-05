package com.lsjyy.nemesis.web.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-02 20:11
 * @Description:
 */
//@Configuration
//public class RedissonConfig {
//
//    @Value("${spring.redis.sentinel.master}")
//    private String redisMaster;
//    @Value("${spring.redis.password}")
//    private String password;
//
//
//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSentinelServers()
//                //服务名称
//                .setMasterName(redisMaster)
//                //哨兵节点
//                .addSentinelAddress("redis://118.89.238.168:23824", "redis://47.104.60.115:23824", "redis://49.234.205.96:23824")
//                //密码
//                .setPassword(password);
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
//}
