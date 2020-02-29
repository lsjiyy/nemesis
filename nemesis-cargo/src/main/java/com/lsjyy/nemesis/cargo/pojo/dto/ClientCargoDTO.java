package com.lsjyy.nemesis.cargo.pojo.dto;

import com.lsjyy.nemesis.cargo.pojo.CargoInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-15 15:22
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCargoDTO implements Serializable {
    private String cargoId;
    private String cargoName;
    private String coverUrl;
    private Integer unitPrice;
    private Integer inventory;
    private String unit;
    private String direction;
    //是否为收藏品
    private boolean favorite;

    public ClientCargoDTO(CargoInfo cargoInfo) {
        this.cargoId = cargoInfo.getCargoId();
        this.cargoName = cargoInfo.getCargoName();
        this.coverUrl = cargoInfo.getCoverUrl();
        this.unitPrice = cargoInfo.getUnitPrice();
        this.inventory = cargoInfo.getInventory();
        this.unit = cargoInfo.getUnit();
        this.direction = cargoInfo.getDirection();
    }
}
