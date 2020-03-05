package com.lsjyy.nemesis.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 21:17
 * @Description:
 */
@Data
public class PlaceOrderVO implements Serializable {
    private String mouseId;
    private String cargoId;
}
