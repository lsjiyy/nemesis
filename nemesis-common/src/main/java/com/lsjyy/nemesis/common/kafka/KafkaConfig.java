package com.lsjyy.nemesis.common.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * @Authoer LsjYy
 * @DATE 2020-03-01 13:15
 * @Description:
 */
@Configuration
public class KafkaConfig {
    private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class);
    private static final String WEB_TOPIC = "web-topic";
    private static final String MOUSE_TOPIC = "mouse-topic";
    private static final String SYSTEM_TOPIC = "system-topic";


    /**
     * name  主题名称
     * numPartitions  分区数
     * replicationFactor 副本数
     *
     * @return
     */
    @Bean
    public List<NewTopic> adviceTopic() {
        List<NewTopic> topicList = new ArrayList<>();
        topicList.add(new NewTopic(WEB_TOPIC, 3, (short) 1));
        topicList.add(new NewTopic(MOUSE_TOPIC, 3, (short) 1));
        topicList.add(new NewTopic(SYSTEM_TOPIC, 3, (short) 1));
        return topicList;
    }
}
