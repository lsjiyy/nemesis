package com.lsjyy.nemesis.cargo.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 20:01
 * @Description:
 */
@Data
public class CargoSlide {
    private String cargoId;
    private String fileUrl;
    private Timestamp createTime;
}
