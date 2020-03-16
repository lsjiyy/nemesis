package com.lsjyy.nemesis.cargo.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 18:18
 * @Description: 货物规格 简版
 */
@Data
public class CargoSpec {
    private Long specId;
    private String cargoId;
    private String specName;
    private String coverUrl;
    private Integer stock;
    private Integer unitPrice;
    private Integer status;
    private Integer dealFlag;
    private Timestamp createTime;
}
