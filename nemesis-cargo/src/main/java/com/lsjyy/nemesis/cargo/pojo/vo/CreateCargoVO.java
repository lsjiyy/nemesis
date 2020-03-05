package com.lsjyy.nemesis.cargo.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 18:45
 * @Description:
 */
@Data
public class CreateCargoVO implements Serializable{
    private String sysUserId;
    private String cargoName;
    private String unit;
    private Integer unitPrice;
    private String explain;
    private String coverUrl;
    private Integer inventory;
    private String direction;
    private List<String> slideList;
}
