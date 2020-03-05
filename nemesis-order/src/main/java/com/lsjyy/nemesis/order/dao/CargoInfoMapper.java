package com.lsjyy.nemesis.order.dao;

import com.lsjyy.nemesis.order.pojo.CargoInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:13
 * @Description:
 */
public interface CargoInfoMapper{

    CargoInfo selectByCargoId(String cargoId);

    int updateInventory(@Param("cargoId") String cargoId,@Param("inventory") Integer inventory);
}
