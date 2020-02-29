package com.lsjyy.nemesis.mouse.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 16:53
 * @Description:
 */
@Data
public class MouseLoginVO implements Serializable {
    private String loginName;
    private String password;
}
