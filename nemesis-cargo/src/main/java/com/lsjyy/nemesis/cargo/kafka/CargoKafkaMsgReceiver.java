package com.lsjyy.nemesis.cargo.kafka;

import com.lsjyy.nemesis.cargo.service.CargoService;
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
public class CargoKafkaMsgReceiver {
    private static final Logger log = LoggerFactory.getLogger(CargoKafkaMsgReceiver.class);
    private static final String WEB_TOPIC = "web-topic";

    @Autowired
    private CargoService cargoService;

    //@KafkaListener(topics = WEB_TOPIC)
    public void onMessage(String message) {
        log.info("收到的消息 ===>{}", message);
        //调用库存处理
        //cargoService.rushCargo(message);
        //insertIntoDb(buffer);//这里为插入数据库代码
    }
}
