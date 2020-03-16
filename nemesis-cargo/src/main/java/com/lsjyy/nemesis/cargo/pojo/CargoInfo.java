package com.lsjyy.nemesis.cargo.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 17:37
 * @Description: 货物基础信息
 */
@Data
public class CargoInfo {
    private String cargoId;
    private Long groupId;
    private String cargoName;
    private String mainCoverUrl;
    private String intro;
    private String direction;
    private Integer status;
    private Integer dealFlag;
    private String unit;
    private Timestamp createTime;
}


