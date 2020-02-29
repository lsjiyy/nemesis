package com.lsjyy.nemesis.system.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 10:56
 * @Description: 系统角色
 */
@Data
public class SysRole {
    private Integer roleId;
    private String roleName;
    private Integer status;
    private Integer dealFlag;
    private String remark;
    private Timestamp updateTime;
    private Timestamp createTime;

}
