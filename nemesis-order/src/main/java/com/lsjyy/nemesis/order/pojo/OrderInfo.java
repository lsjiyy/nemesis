package com.lsjyy.nemesis.order.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-05 15:00
 * @Description:
 */
@Data
public class OrderInfo {
    private String orderId;
    private String cargoId;
    private String orderName;
    private String mouseId;
    private Integer count;
    private Integer totalMoney;
    private Integer payMoney;
    private Integer couponMoney;
    private String addressId;
    private Integer status;
    private Timestamp payTime;
    private Timestamp createTime;
}
