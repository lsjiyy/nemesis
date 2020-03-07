package com.lsjyy.nemesis.common.kafka;

import com.lsjyy.nemesis.common.domain.kafka.KafkaTopic;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class KafkaConfig {
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
        topicList.add(new NewTopic(KafkaTopic.WEB_TOPIC.name(), 3, (short) 1));
        topicList.add(new NewTopic(KafkaTopic.MOUSE_TOPIC.name(), 3, (short) 1));
        topicList.add(new NewTopic(KafkaTopic.SYSTEM_TOPIC.name(), 3, (short) 1));
        topicList.add(new NewTopic(KafkaTopic.SYSTEM_TOPIC.name(), 3, (short) 1));
        return topicList;
    }
}
