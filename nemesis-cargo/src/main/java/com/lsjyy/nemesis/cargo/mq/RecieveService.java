package com.lsjyy.nemesis.cargo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-29 10:41
 * @Description:
 */

@EnableBinding(Sink.class)
public class RecieveService {
    private static final Logger log = LoggerFactory.getLogger(RecieveService.class);

    @StreamListener(Sink.INPUT)
    public void recieve(Message<String> payload) {
        System.out.println(payload.getPayload());
    }

}
