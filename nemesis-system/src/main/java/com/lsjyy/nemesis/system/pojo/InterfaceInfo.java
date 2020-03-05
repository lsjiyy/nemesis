package com.lsjyy.nemesis.system.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-09 18:53
 * @Description: 接口信息实体类
 */
@Data
public class InterfaceInfo {
    private String interfaceId;
    private String path;
    private String method;
    private String serviceId;
    private String remark;
    private Integer tokenFlag;
    private Integer interfaceType;
    private Timestamp updateTime;
    private Timestamp createTime;
}
