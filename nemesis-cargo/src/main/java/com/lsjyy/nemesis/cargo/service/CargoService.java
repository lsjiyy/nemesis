package com.lsjyy.nemesis.cargo.service;


import com.lsjyy.nemesis.cargo.pojo.dto.BackCargoDTO;
import com.lsjyy.nemesis.cargo.pojo.dto.GroupDTO;
import com.lsjyy.nemesis.cargo.pojo.vo.CreateCargoVO;
import com.lsjyy.nemesis.cargo.pojo.vo.CreateGroupVO;
import com.lsjyy.nemesis.cargo.pojo.vo.RevampGroupVO;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:19
 * @Description:
 */
public interface CargoService {
    void createGroup(CreateGroupVO vo);

    List<GroupDTO> getGroup();

    void createCargo(CreateCargoVO vo);

    List<BackCargoDTO> backCargo(String cargoName);

    void revampGroup(RevampGroupVO vo);
}
