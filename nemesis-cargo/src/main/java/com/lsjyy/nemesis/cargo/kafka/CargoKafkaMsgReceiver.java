package com.lsjyy.nemesis.cargo.kafka;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.cargo.service.CargoService;
import com.lsjyy.nemesis.common.domain.kafka.KafkaConstant;
import com.lsjyy.nemesis.common.domain.kafka.KafkaTopic;
import com.lsjyy.nemesis.common.kafka.KafkaMessage;
import com.lsjyy.nemesis.common.kafka.KafkaRetry;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.common.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-01 13:50
 * @Description:
 */
@Component
@Slf4j
public class CargoKafkaMsgReceiver {
    private static final String ORDER_TOPIC = "ORDER_TOPIC";

    @Autowired
    private CargoService cargoService;
    @Autowired
    private KafkaRetry retry;


    @KafkaListener(topics = ORDER_TOPIC)
    public void onMessage(ConsumerRecord record, Acknowledgment ack) {
        KafkaMessage message = JSONObject.parseObject(record.value().toString(), KafkaMessage.class);
        log.info("收到的消息 ===>{}", message.getData());
        try {
            cargoService.reduceInventory(message);
            //业务代码
        } catch (Exception e) {
            e.printStackTrace();
            message.setCreateTime(record.timestamp());
            retry.record(ORDER_TOPIC, message);
        } finally {
            //手动提交
            ack.acknowledge();
        }
    }
}
