package com.lsjyy.nemesis.system.config;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.InterfaceType;
import com.lsjyy.nemesis.common.redis.RedisKey;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.common.role.InterfacePath;
import com.lsjyy.nemesis.system.dao.InterfaceInfoMapper;
import com.lsjyy.nemesis.system.pojo.InterfaceInfo;
import com.lsjyy.nemesis.system.pojo.ServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-09 19:42
 * @Description:
 */
@Component
@EnableScheduling
public class InitializeData implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(InitializeData.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private InterfaceInfoMapper interfaceMapper;

    /**
     * 每两小时加载一次数据到缓存内
     * 将数据加载到缓存中,使用redis
     */
    //@Scheduled(cron = "0 0 */2 * * ?")
    @Scheduled(fixedRate = 50000)
    public void loadDataCache() {
        //服务端接口
        redisUtil.deleteKey(RedisKey.SYS_INTERFACE);
        List<InterfacePath> pathList = interfaceMapper.selectTokenPath();
        pathList.forEach(value -> {
            redisUtil.pushList(RedisKey.SYS_INTERFACE, JSONObject.toJSONString(value));
        });

    }


    @Override
    @Order(1)
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            loadDataCache();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
