package com.lsjyy.nemesis.mouse.pojo.vo;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 12:13
 * @Description: 小老鼠注册
 */
@Data
public class RegisterMouseVO implements Serializable {
    private String loginName;
    private String password;
    private String email;
}
