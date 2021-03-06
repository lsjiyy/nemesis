package com.lsjyy.nemesis.order.kafka;

import com.lsjyy.nemesis.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-05 18:22
 * @Description:
 */
@Component
public class OrderKafkaMsgReceiver {
    private static final Logger log = LoggerFactory.getLogger(OrderKafkaMsgReceiver.class);
    private static final String WEB_TOPIC = "WEB_TOPIC";

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = WEB_TOPIC)
    public void onMessage(String message, Acknowledgment ack) throws Exception {
        log.info("order from {}, ===>{}", WEB_TOPIC, message);
        try {
            if (StringUtils.isEmpty(message))
                return;
            orderService.rushOrder(message);
        } catch (Exception e) {

        } finally {
            ack.acknowledge();
        }

    }
}
