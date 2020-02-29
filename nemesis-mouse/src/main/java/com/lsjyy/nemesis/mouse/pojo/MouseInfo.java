package com.lsjyy.nemesis.mouse.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 11:52
 * @Description:
 */
@Data
public class MouseInfo {
    private String mouseId;
    private String nickName;
    private String loginName;
    private String password;
    private String email;
    private Integer status;
    private Integer star;
    private Timestamp registerTime;
}
