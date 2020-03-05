package com.lsjyy.nemesis.system.dao;

import com.lsjyy.nemesis.common.role.InterfacePath;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 13:51
 * @Description:
 */
public interface InterfaceInfoMapper {
    /**
     * 获取需要验证的接口
     * @return
     */
    List<InterfacePath> selectTokenPath();

}
