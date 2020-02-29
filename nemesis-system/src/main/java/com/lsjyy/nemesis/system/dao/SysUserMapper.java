package com.lsjyy.nemesis.system.dao;

import com.lsjyy.nemesis.system.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 12:52
 * @Description:
 */
public interface SysUserMapper {
    /**
     * 通过登录名查询
     *
     * @param loginName
     * @return
     */
    SysUser selectByLoginName(String loginName);

    void updateLogin(@Param("sysUserId") String sysUserId,@Param("ip") String ip);
}
