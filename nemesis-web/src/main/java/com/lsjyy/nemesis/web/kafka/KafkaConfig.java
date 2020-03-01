package com.lsjyy.nemesis.web.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * @Authoer LsjYy
 * @DATE 2020-03-01 13:15
 * @Description:
 */
@Configuration
public class KafkaConfig {
    private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class);
    private static final  String topic = "nemesis-topic";



    /**
     * name  主题名称
     * numPartitions  分区数
     * replicationFactor 副本数
     * @return
     */
    @Bean
    public NewTopic adviceTopic() {
        return new NewTopic(topic, 3, (short) 1);
    }
}
