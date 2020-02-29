package com.lsjyy.nemesis.system.pojo;

import com.lsjyy.nemesis.common.utils.StringUtil;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-07 22:58
 * @Description: 系统版本记录
 */
@Data
public class SystemVersionHistory {
    private String serializeId;
    private String versionNum;
    private Timestamp setTime;
    private String direction;
    private Integer status;
    private String nemesisId;
}
