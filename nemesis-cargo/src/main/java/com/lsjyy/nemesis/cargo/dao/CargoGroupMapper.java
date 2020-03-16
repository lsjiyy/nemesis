package com.lsjyy.nemesis.cargo.dao;

import com.lsjyy.nemesis.cargo.pojo.CargoGroup;
import com.lsjyy.nemesis.cargo.pojo.vo.RevampGroupVO;

import java.util.List;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 18:38
 * @Description:
 */
public interface CargoGroupMapper {
    CargoGroup selectGroupName(String groupName);

    CargoGroup selectGroupId(Long groupId);

    int insert(CargoGroup cargoGroup);

    List<CargoGroup> selectUpGroupId(Long upGroupId);

    int updateGroup(RevampGroupVO revampGroupVO);
}
