package com.lsjyy.nemesis.email.kafka;

import com.lsjyy.nemesis.email.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-01 13:50
 * @Description:
 */
@Component
public class EmailKafkaMsgReceiver {
    private static final Logger log = LoggerFactory.getLogger(EmailKafkaMsgReceiver.class);
    private static final String MOUSE_TOPIC = "mouse-topic";
    private static final String WEB_TOPIC = "web-topic";

    @Autowired
    private MailService mailService;

    @KafkaListener(topics = MOUSE_TOPIC)
    public void onMouseMessage(String message) throws  Exception {
        log.info("收到的消息 ===>{}", message);
        //接收到消息
        mailService.receiveContent(message);
    }

    @KafkaListener(topics = WEB_TOPIC)
    public void testMessage(String message) throws  Exception{
        log.info("收到的消息 ===>{}", message);
        //接收到消息
        mailService.receiveContent(message);
    }
}
