package com.lsjyy.nemesis.mouse.dao;

import com.lsjyy.nemesis.mouse.pojo.MouseInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 12:58
 * @Description:
 */
public interface MouseInfoMapper {
    boolean existLoginName(String loginName);

    boolean existEmail(String email);

    void insert(MouseInfo mouseInfo);

    MouseInfo selectByEmail(String email);

    void updateStatus(@Param("mouseId") String mouseId, @Param("status") int status);

    MouseInfo selectByLoginName(String loginName);

    MouseInfo selectByMouseId(String mouseId);
}
