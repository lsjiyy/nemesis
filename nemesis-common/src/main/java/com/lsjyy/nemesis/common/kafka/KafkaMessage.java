package com.lsjyy.nemesis.common.kafka;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-07 15:22
 * @Description:
 */
@Data
public class KafkaMessage {
    private String messageId;
    private String data;
    private Long createTime;
}
