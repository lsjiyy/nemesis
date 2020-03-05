package com.lsjyy.nemesis.system.kafka;

import com.lsjyy.nemesis.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 19:33
 * @Description:
 */
@Component
public class SystemKafkaReceiver {
    private static final Logger log = LoggerFactory.getLogger(SystemKafkaReceiver.class);
    private static final String SYSTEM_TOPIC = "system-topic";

    @Autowired
    private SystemService systemService;

    @KafkaListener(topics = SYSTEM_TOPIC)
    public void onMessage(String message) {
        log.info("收到的消息 ===>{}", message);
        systemService.recordLog(message);
    }
}
