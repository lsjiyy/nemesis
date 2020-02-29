package com.lsjyy.nemesis.mouse.pojo.dto;

import com.lsjyy.nemesis.common.jwt.Token;
import com.lsjyy.nemesis.mouse.pojo.MouseInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 16:38
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MouseInfoDto implements Serializable {
    private String mouseId;
    private String loginName;
    private String nickName;
    private String headIcon;
    private Integer star;
    private Token token;
    private Integer status;

    public MouseInfoDto(MouseInfo mouseInfo) {
        this.mouseId = mouseInfo.getMouseId();
        this.loginName = mouseInfo.getLoginName();
        this.nickName = mouseInfo.getNickName();
        this.headIcon = "";
        this.status = mouseInfo.getStatus();
        this.star = mouseInfo.getStar();
    }
}
