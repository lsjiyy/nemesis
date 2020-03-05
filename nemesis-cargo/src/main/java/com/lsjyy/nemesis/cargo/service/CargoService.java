package com.lsjyy.nemesis.cargo.service;

import com.lsjyy.nemesis.cargo.pojo.dto.CargoSampleDTO;
import com.lsjyy.nemesis.cargo.pojo.dto.ClientCargoDTO;
import com.lsjyy.nemesis.cargo.pojo.vo.ClientCargoVO;
import com.lsjyy.nemesis.cargo.pojo.vo.CreateCargoVO;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:19
 * @Description:
 */
public interface CargoService {
    /**
     * 缓存预热
     */
    void cacheCargoInventory();

    List<CargoSampleDTO> getCargoList();

    ClientCargoDTO clientCargoInfo(ClientCargoVO vo);

    void createCargo(CreateCargoVO vo);
}
