package com.lsjyy.nemesis.web.controller;

import com.lsjyy.nemesis.common.aop.Logging;
import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.web.mq.SendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    private SendService sendService;

    @Resource
    private RedisUtil redisUtil;

    @Logging(module = "web", operateExplain = "测试")
    @GetMapping
    public AjaxResult test(String message) throws Exception {
        sendService.sendMsg(message);

        return AjaxResult.success();
    }


    @GetMapping("/t")
    public AjaxResult testt() throws Exception {
        log.info("被调用了");
        //msgProducer.SendMsg("请接收消息");
        //测试 要抢购的货品
        // Object obj = redisUtil.getValue(RedisKey.CARGO+"c2");
        //log.info("obj ===>{}",obj);
        // if (obj != null) {
        //msgProducer.sendSecKillMsg("c2");
        //}else{
        // }

        //redisUtil.setValue("test","testvalue");
        redisUtil.getValue("test1");


        return AjaxResult.success(redisUtil.getValue("test1"));
    }
}
