package com.lsjyy.nemesis.cargo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsjyy.nemesis.cargo.pojo.CargoInfo;
import com.lsjyy.nemesis.cargo.pojo.dto.CargoSampleDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:13
 * @Description:
 */
public interface CargoInfoMapper extends BaseMapper<CargoInfo>{
    List<CargoSampleDTO> selectNormalSample();

    int insert(CargoInfo cargoInfo);

    void updateInventory(@Param("cargoId") String cargoId, @Param("inventory") int inventory);

    CargoInfo selectByCargoId(String cargoId);
}
