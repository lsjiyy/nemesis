package com.lsjyy.nemesis.cargo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsjyy.nemesis.cargo.pojo.CollectCargo;
import org.apache.ibatis.annotations.Param;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-15 15:49
 * @Description:
 */

public interface CollectCargoMapper extends BaseMapper<CollectCargo>{
    boolean exist(@Param("cargoId") String cargoId, @Param("mouseId") String mouseId);
}
