package com.lsjyy.nemesis.system.dao;

import com.lsjyy.nemesis.system.pojo.dto.UserRoleInterfaceDTO;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 13:34
 * @Description:
 */
public interface SysUserRoleMapper {
    UserRoleInterfaceDTO selectUserInterface(String sysUserId);
}
