package com.lsjyy.nemesis.system.pojo;

import com.lsjyy.nemesis.common.domain.log.RecordLogVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-13 18:07
 * @Description:
 */
@Data
@NoArgsConstructor
public class OperateHistory {
    private String historyId;
    private String sysUserId;
    private String recordModule;
    private String operateType;
    private String args;
    private String status;
    private String ip;
    private Timestamp createTime;

    public OperateHistory(RecordLogVO vo) {
        this.sysUserId = vo.getSysUserId();
        this.recordModule = vo.getModule();
        this.operateType = vo.getOperateType();
        this.args = vo.getArgs();
        this.status = vo.getStatus();
        this.ip = vo.getIp();
        this.createTime = vo.getRecordTime();
    }
}
