package com.lsjyy.nemesis.system.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 11:24
 * @Description: 系统登录请求参数
 */
@Data
public class SysUserLoginVO implements Serializable{
    private String loginName;
    private String password;
}
