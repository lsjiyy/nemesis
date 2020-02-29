package com.lsjyy.nemesis.system.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 10:53
 * @Description: 系统用户
 */
@Data
public class SysUser {
    private String sysUserId;
    private String userName;
    private String loginName;
    private String password;
    private String email;
    private String loginIp;
    private Timestamp loginTime;
    private String remark;
    private Integer status;
    private Integer dealFlag;
    private Timestamp createTime;
}
