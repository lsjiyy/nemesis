package com.lsjyy.nemesis.system.pojo.dto;

import com.lsjyy.nemesis.common.jwt.Token;
import lombok.Data;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 12:46
 * @Description:
 */
@Data
public class SysUserDTO {
    private String sysUserId;
    private String userName;
    private Token token;
}
