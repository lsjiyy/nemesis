package com.lsjyy.nemesis.order.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 17:37
 * @Description: 货物信息
 */
@Data
public class CargoInfo {
    private String cargoId;
    private String cargoName;
    private String coverUrl;
    private Integer status;
    private Integer dealFlag;
    private String unit;
    private Integer unitPrice;
    private String direction;
    private String explain;
    private Integer inventory;
    private Timestamp updateTime;
    private Timestamp createTime;
}


