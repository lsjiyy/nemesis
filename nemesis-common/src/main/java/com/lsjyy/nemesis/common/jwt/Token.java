package com.lsjyy.nemesis.common.jwt;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-09 10:06
 * @Description: token实体类
 */
@Data
public class Token {
    private String accessToken;
    private Timestamp expTime;
}
