package com.lsjyy.nemesis.email.mq;

import com.lsjyy.nemesis.common.mq.RabbitMqConfig;
import com.lsjyy.nemesis.email.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 15:25
 * @Description:
 */
@Component
public class MsgReceiver {
    private static final Logger log = LoggerFactory.getLogger(MsgReceiver.class);

    @Autowired
    private MailService mailService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_EMAIL)
    @RabbitHandler
    public void processQUEUE_C(String content) {
        log.info("contentB ===>{}", content);
        mailService.receiveContent(content);
    }
}
