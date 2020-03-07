package com.lsjyy.nemesis.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-07 18:40
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaErrorMessage {
    private String id;
    private String topic;
    private String data;
    private Long count;
    private Timestamp createTime;
}
