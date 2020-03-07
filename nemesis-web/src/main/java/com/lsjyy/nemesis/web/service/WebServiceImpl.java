package com.lsjyy.nemesis.web.service;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.kafka.KafkaConstant;
import com.lsjyy.nemesis.common.domain.kafka.KafkaTopic;
import com.lsjyy.nemesis.common.kafka.KafkaMessage;
import com.lsjyy.nemesis.common.kafka.KafkaMsgProducer;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.web.vo.PlaceOrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 21:14
 * @Description:
 */
@Service
@EnableScheduling
public class WebServiceImpl implements WebService {
    private static final Logger log = LoggerFactory.getLogger(WebServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private KafkaMsgProducer msgProducer;


    @Override
    public void rushCargo(PlaceOrderVO vo) {
        log.info("vo ===>{}", vo);
        msgProducer.sendMessage(KafkaTopic.WEB_TOPIC.name(), JSONObject.toJSONString(vo));
    }

}
