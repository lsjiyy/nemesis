package com.lsjyy.nemesis.cargo.pojo.dto;

import lombok.Data;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:24
 * @Description: 商品列表信息
 */
@Data
public class CargoSampleDTO {
    private String cargoId;
    private String cargoName;
    private String coverUrl;
    private Integer unitPrice;
    private Integer inventory;
    private String unit;
}
