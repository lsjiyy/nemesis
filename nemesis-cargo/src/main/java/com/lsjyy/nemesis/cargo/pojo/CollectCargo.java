package com.lsjyy.nemesis.cargo.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-15 15:48
 * @Description:
 */
@Data
public class CollectCargo {
    private String mouseId;
    private String cargoId;
    private Timestamp createTime;
}
