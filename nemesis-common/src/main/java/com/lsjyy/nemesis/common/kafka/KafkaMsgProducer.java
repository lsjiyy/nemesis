package com.lsjyy.nemesis.common.kafka;

import com.lsjyy.nemesis.common.domain.kafka.KafkaTopic;
import com.lsjyy.nemesis.common.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-01 14:08
 * @Description:
 */
@Component
@Slf4j
public class KafkaMsgProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        log.info("要发送的消息 ===>{}", message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(success -> log.info("KafkaMessageProducer 发送消息成功！"),
                fail -> this.sendMessage(topic, message));
    }

}
