package com.lsjyy.nemesis.common.kafka;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.kafka.KafkaConstant;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.common.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lsjyy.nemesis.common.domain.kafka.KafkaTopic.ORDER_TOPIC;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-07 15:03
 * @Description:
 */
@Component
@Slf4j
public class KafkaRetry {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private KafkaMsgProducer msgProducer;

    public void record(String topic, KafkaMessage message) {
        log.info("topic ===>{},value ===>{}", topic, message.getMessageId());
        //判断redis中是否有消息
        boolean flag = redisUtil.hasKey("kafka" + message.getMessageId());
        if (flag) {
            return;
        }
        Map<String, Object> map = new HashMap<>();
        message.setMessageId(SnowFlake.generateId());
        map.put(KafkaConstant.data.name(), message.getData());
        map.put(KafkaConstant.count.name(), 0);
        map.put(KafkaConstant.topic.name(), topic);
        map.put(KafkaConstant.id.name(), message.getMessageId());
        map.put(KafkaConstant.time.name(), message.getCreateTime());
        redisUtil.addHash("kafka" + message.getMessageId(), map);
    }


}
