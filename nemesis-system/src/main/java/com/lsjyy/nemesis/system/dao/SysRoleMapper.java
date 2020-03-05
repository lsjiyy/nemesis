package com.lsjyy.nemesis.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsjyy.nemesis.system.pojo.SysRole;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 16:30
 * @Description:
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<String> selectUsrRole();
}
