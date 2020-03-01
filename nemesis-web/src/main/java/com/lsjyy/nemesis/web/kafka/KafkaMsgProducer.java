package com.lsjyy.nemesis.web.kafka;

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
public class KafkaMsgProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaMsgProducer.class);
    private static final  String topic = "nemesis-topic";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("要发送的消息 ===>{}", message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(success -> log.info("KafkaMessageProducer 发送消息成功！"),
                fail -> this.sendM(message));
    }

    public void sendM(String message) {
        kafkaTemplate.send("myTopic", message);
    }
}