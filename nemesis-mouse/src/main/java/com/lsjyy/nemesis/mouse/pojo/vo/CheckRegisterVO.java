package com.lsjyy.nemesis.mouse.pojo.vo;

import lombok.Data;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 16:23
 * @Description: 校验验证码, 邮箱
 */
@Data
public class CheckRegisterVO {
    private String email;
    private String code;
}
