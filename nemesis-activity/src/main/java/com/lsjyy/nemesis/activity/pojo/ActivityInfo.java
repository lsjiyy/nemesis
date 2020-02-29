package com.lsjyy.nemesis.activity.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-13 19:00
 * @Description:
 */
@Data
public class ActivityInfo {
    private String activityId;
    private String explain;
    private Integer activityType;
    private String coverUrl;
    private String jumpUrl;
    private Integer status;
    private Integer dealFlah;
    private Timestamp updateTime;
    private Timestamp createTime;
}
