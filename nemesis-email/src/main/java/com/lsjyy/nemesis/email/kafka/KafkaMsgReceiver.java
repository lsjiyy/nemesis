package com.lsjyy.nemesis.email.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-01 13:50
 * @Description:
 */
@Component
public class KafkaMsgReceiver {
    private static final Logger log = LoggerFactory.getLogger(KafkaMsgReceiver.class);
    private static final  String topic = "nemesis-topic";

    @KafkaListener(topics = topic)
    public void onMessage(String message) {
        log.info("收到的消息 ===>{}", message);
        //insertIntoDb(buffer);//这里为插入数据库代码
    }
}
