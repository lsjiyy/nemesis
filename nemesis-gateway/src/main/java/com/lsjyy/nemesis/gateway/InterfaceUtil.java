package com.lsjyy.nemesis.gateway;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.InterfaceType;
import com.lsjyy.nemesis.common.redis.RedisKey;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.common.role.InterfacePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 13:41
 * @Description:
 */
@Component
@EnableScheduling
public class InterfaceUtil {
    private static final Logger log = LoggerFactory.getLogger(InterfaceUtil.class);

    @Autowired
    private RedisUtil redisUtil;

    public String checkInterface(String path, String method) {
        //接口缓存中有此接口
        List<Object> list = redisUtil.getRangeList(RedisKey.SYS_INTERFACE, 0L, -1L);
        for (Object object : list) {
            InterfacePath interfacePath = JSONObject.parseObject(object.toString(), InterfacePath.class);
            if (path.equals("/" + interfacePath.getServiceName() + interfacePath.getPath()) && method.equals(interfacePath.getMethod())) {
                return interfacePath.getInterfaceId();
            }
        }
        return null;
    }

    @Scheduled(fixedDelay = 60000L )
    public void testRedis(){
        redisUtil.getStringValue("123");
        log.info("测试连接");
    }
}
