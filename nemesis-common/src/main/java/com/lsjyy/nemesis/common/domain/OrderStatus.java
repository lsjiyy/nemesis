package com.lsjyy.nemesis.common.domain;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-05 19:47
 * @Description:
 */
public class OrderStatus {
    public static final Integer WAIT = 1;  //待支付
    public static final Integer PAYING = 2; //支付中
    public static final Integer FINISH = 3; //完成
    public static final Integer CANCEL = 4; //取消
}
