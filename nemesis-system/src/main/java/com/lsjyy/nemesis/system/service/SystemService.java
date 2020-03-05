package com.lsjyy.nemesis.system.service;

import com.lsjyy.nemesis.system.pojo.dto.ServerStatusDTO;
import com.lsjyy.nemesis.system.pojo.dto.SysUserDTO;
import com.lsjyy.nemesis.system.pojo.vo.SysUserLoginVO;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 10:59
 * @Description:
 */
public interface SystemService {
    SysUserDTO login(SysUserLoginVO loginVO, String ip) throws Exception;

    void refreshInterface();

    void recordLog(String content);

    List<String> getUsrRole();
}
