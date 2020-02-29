package com.lsjyy.nemesis.common.mq;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
///**
// * @Authoer LsjYy
// * @DATE 2020-02-09 16:14
// * @Description: 消息生产者
// */
//@Component
//public class MsgProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
//    private static final Logger log = LoggerFactory.getLogger(MsgProducer.class);
//
//    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
//    private RabbitTemplate rabbitTemplate;
//
//    //构造方法注入
//    @Autowired
//    public MsgProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
//    }
//
//    public void sendSecKillMsg(String content) {
//        log.info("SendContent===>{}", content);
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
//        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_A, RabbitMqConfig.ROUTINGKEY_A, content, correlationId);
//    }
//
//    public void sendOperateLogMsg(String content){
//        log.info("LOG content===>{}", content);
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        //把消息放入ROUTINGKEY_C对应的队列当中去，对应的是队列C
//        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_B, RabbitMqConfig.ROUTINGKEY_B, content, correlationId);
//    }
//
//    public void sendEmailMsg(String content){
//        log.info("email content===>{}", content);
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        //把消息放入ROUTINGKEY_C对应的队列当中去，对应的是队列C
//        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_C, RabbitMqConfig.ROUTINGKEY_C, content, correlationId);
//    }
//
//    /**
//     * 当消息发送到交换机（exchange）时，该方法被调用.
//     * 1.如果消息没有到exchange,则 ack=false
//     * 2.如果消息到达exchange,则 ack=true
//     *
//     * @param correlationData
//     * @param ack
//     * @param cause
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        log.info(" 回调id:" + correlationData);
//        if (ack) {
//            log.info("消息到达exchange");
//        } else {
//            log.error("消息发送失败:" + cause);
//        }
//    }
//
//    @Override
//    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
//        log.error("MsgSendReturnCallback [消息从交换机到队列失败]  message：" + message);
//    }
//}
//