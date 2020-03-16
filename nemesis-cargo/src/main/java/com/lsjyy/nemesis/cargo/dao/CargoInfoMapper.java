package com.lsjyy.nemesis.cargo.dao;


import com.lsjyy.nemesis.cargo.pojo.CargoInfo;
import com.lsjyy.nemesis.cargo.pojo.dto.BackCargoDTO;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:13
 * @Description:
 */
public interface CargoInfoMapper {
    void insert(CargoInfo cargoInfo);

    List<BackCargoDTO> selectBackCaro(String cargoName);
}
