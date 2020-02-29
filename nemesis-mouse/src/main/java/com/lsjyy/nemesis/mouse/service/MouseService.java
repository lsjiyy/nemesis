package com.lsjyy.nemesis.mouse.service;

import com.lsjyy.nemesis.mouse.pojo.dto.MouseInfoDto;
import com.lsjyy.nemesis.mouse.pojo.vo.CheckRegisterVO;
import com.lsjyy.nemesis.mouse.pojo.vo.MouseLoginVO;
import com.lsjyy.nemesis.mouse.pojo.vo.RegisterMouseVO;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 12:09
 * @Description:
 */
public interface MouseService {
    void register(RegisterMouseVO vo) throws Exception;

    MouseInfoDto checkRegisterCode(CheckRegisterVO vo) throws Exception;

    MouseInfoDto login(MouseLoginVO vo)throws Exception;

    void activityAccount(String mouseId);
}
