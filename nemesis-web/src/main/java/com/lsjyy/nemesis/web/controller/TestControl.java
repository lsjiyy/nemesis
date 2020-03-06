package com.lsjyy.nemesis.web.controller;

import com.lsjyy.nemesis.common.aop.log.Logging;
import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.kafka.KafkaMsgProducer;
import com.lsjyy.nemesis.common.redis.RedisKey;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-08 10:37
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestControl {
    private static final Logger log = LoggerFactory.getLogger(TestControl.class);

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private KafkaMsgProducer msgProducer;

    @Resource
    private RedisUtil redisUtil;


    @Value("${spring.pay}")
    private String pay;

    @GetMapping
    public AjaxResult test() throws Exception {
        log.info("success");
        return AjaxResult.success(pay);
    }


    @GetMapping("/t")
    public AjaxResult testCargo(String cargoId) throws Exception {


        RLock lock = redissonClient.getLock("sk" + cargoId);
        try {
            //TODO:第一个参数30s=表示尝试获取分布式锁，并且最大的等待获取锁的时间为30s
            //TODO:第二个参数10s=表示上锁之后，10s内操作完毕将自动释放锁
            Boolean cache = lock.tryLock(30, 15, TimeUnit.SECONDS);
            if (cache) {
                Object object = redisUtil.getStringValue(RedisKey.RUSH + cargoId);

                if (Objects.isNull(object)) {
                    return AjaxResult.warn("商品已抢购完毕");
                }
                long count = Long.valueOf(object.toString());
                if (count > 0) {
                    count = redisUtil.increment(RedisKey.RUSH + cargoId, -1);
                    //msgProducer.sendMessage(cargoId);
                    if (count <= 0) {
                        //redisUtil.deleteKey(RedisKey.CARGO + cargoId);
                        return AjaxResult.success("已经没货了");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return AjaxResult.success("有货");
    }
}
