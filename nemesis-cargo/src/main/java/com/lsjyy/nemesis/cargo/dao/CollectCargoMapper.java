package com.lsjyy.nemesis.cargo.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-15 15:49
 * @Description:
 */

public interface CollectCargoMapper {
    boolean exist(@Param("cargoId") String cargoId, @Param("mouseId") String mouseId);
}
