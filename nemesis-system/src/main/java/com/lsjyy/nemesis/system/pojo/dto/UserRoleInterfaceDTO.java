package com.lsjyy.nemesis.system.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 13:32
 * @Description: 用户适配接口I
 */
@Data
public class UserRoleInterfaceDTO {
    private String sysUserId;
    private List<String> interfaceIdList;
}
