package com.lsjyy.nemesis.system.mq;

//import com.lsjyy.nemesis.common.mq.RabbitMqConfig;
//import com.lsjyy.nemesis.system.service.SystemService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @Authoer LsjYy
// * @DATE 2020-02-13 14:33
// * @Description:
// */
//@Component
//public class MsgReceiver {
//    private static final Logger log = LoggerFactory.getLogger(MsgReceiver.class);
//
//    @Autowired
//    private SystemService systemService;
//
//    @RabbitListener(queues = RabbitMqConfig.QUEUE_LOG)
//    @RabbitHandler
//    public void processQUEUE_B(String content) {
//        log.info("contentB ===>{}", content);
//        systemService.recordLog(content);
//    }
//}
//