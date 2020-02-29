package com.lsjyy.nemesis.web.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-29 10:33
 * @Description:
 */

/**
 *  绑定消息通道
 */
@EnableBinding(MySource.class)
public class SendService {
    private static Logger log = LoggerFactory.getLogger(SendService.class);

    @Autowired
    private MySource source;

    public void sendMsg(String msg) {
       Message<String> message= MessageBuilder.withPayload(msg).build();
       message.getPayload();
        log.info("send msg ===>{}",message.getPayload());
        source.myOutput().send(message);
    }

}
