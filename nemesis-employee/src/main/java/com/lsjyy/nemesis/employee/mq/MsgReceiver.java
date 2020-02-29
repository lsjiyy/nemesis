package com.lsjyy.nemesis.employee.mq;

import com.lsjyy.nemesis.common.mq.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/*
 * @Authoer LsjYy
 * @DATE 2019-10-29 13:40
 * @Description: 消息消费者
 *
 * 当多个消费者消费同一队列时,消息随机被其中一个消费者消费
 */

@Component
public class MsgReceiver {
    private final Logger log = LoggerFactory.getLogger(MsgReceiver.class);

/*
    @RabbitListener(queues = RabbitMqConfig.QUEUE_A)
    @RabbitHandler
    public void processQUEUE_A(String content) {
        log.info("contentA ===>{}",content);
    }
*/

    @RabbitListener(queues = RabbitMqConfig.QUEUE_ORDER)
    @RabbitHandler
    public void processQUEUE_B(String content) {
        log.info("contentB ===>{}",content);
    }

}
