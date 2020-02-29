package com.lsjyy.nemesis.cargo.service;

import com.lsjyy.nemesis.cargo.pojo.dto.CargoSampleDTO;
import com.lsjyy.nemesis.cargo.pojo.dto.ClientCargoDTO;
import com.lsjyy.nemesis.cargo.pojo.vo.ClientCargoVO;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:19
 * @Description:
 */
public interface CargoService {
    /**
     * 缓存商品列表
     */
    void cacheCargoList();

    List<CargoSampleDTO> getCargoList();

    void rushCargo(String cargoId);

    ClientCargoDTO clientCargoInfo(ClientCargoVO vo);
}
