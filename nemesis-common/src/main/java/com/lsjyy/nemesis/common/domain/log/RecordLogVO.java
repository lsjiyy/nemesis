package com.lsjyy.nemesis.common.domain.log;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-13 15:43
 * @Description:
 */
@Data
public class RecordLogVO {
    private String sysUserId;
    private String module;
    private String operateType;
    private String args;
    private Timestamp recordTime;
    private String ip;
    private String status;
}
