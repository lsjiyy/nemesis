package com.lsjyy.nemesis.cargo.mq;

//import com.lsjyy.nemesis.cargo.service.CargoService;
//import com.lsjyy.nemesis.common.mq.RabbitMqConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @Authoer LsjYy
// * @DATE 2020-02-12 10:22
// * @Description:
// */
//@Component
//public class MsgReceiver {
//    private static final Logger log = LoggerFactory.getLogger(MsgReceiver.class);
//
//    @Autowired
//    private CargoService cargoService;
//
//    @RabbitListener(queues = RabbitMqConfig.QUEUE_ORDER)
//    @RabbitHandler
//    public void processQUEUE_A(String content) {
//        log.info("收到消息");
//        cargoService.rushCargo(content);
//    }
//
//}
//