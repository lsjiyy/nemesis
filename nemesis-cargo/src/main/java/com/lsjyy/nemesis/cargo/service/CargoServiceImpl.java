package com.lsjyy.nemesis.cargo.service;

import com.lsjyy.nemesis.cargo.dao.*;
import com.lsjyy.nemesis.cargo.exception.CargoException;
import com.lsjyy.nemesis.cargo.pojo.*;
import com.lsjyy.nemesis.cargo.pojo.dto.BackCargoDTO;
import com.lsjyy.nemesis.cargo.pojo.dto.GroupDTO;
import com.lsjyy.nemesis.cargo.pojo.vo.*;
import com.lsjyy.nemesis.common.domain.StatusType;
import com.lsjyy.nemesis.common.page.PageResult;
import com.lsjyy.nemesis.common.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:19
 * @Description:
 */
@Service
@EnableScheduling
@Slf4j
public class CargoServiceImpl implements CargoService {


    @Autowired
    private CargoGroupMapper groupMapper;
    @Autowired
    private CargoInfoMapper infoMapper;
    @Autowired
    private CargoSpecMapper specMapper;


    /**
     * 添加时删除缓存
     *
     * @param vo
     */
    @CacheEvict(allEntries = true, value = "cargoGroupCache")
    @Override
    public void createGroup(CreateGroupVO vo) {
        log.info("request ===>{}", vo);
        CargoGroup groupName = groupMapper.selectGroupName(vo.getGroupName());
        if (!Objects.isNull(groupName)) {
            throw new CargoException("分类名称重复");
        }
        if (!Objects.isNull(vo.getUpGroupId())) {
            CargoGroup upGroup = groupMapper.selectGroupId(vo.getUpGroupId());
            if (Objects.isNull(upGroup))
                throw new CargoException("所选的上级分类不存在");
        }
        CargoGroup group = new CargoGroup();
        group.setUpGroupId(Objects.isNull(vo.getUpGroupId()) ? 0 : vo.getUpGroupId());
        group.setGroupName(vo.getGroupName());
        group.setStatus(StatusType.NORMAL);
        int result = groupMapper.insert(group);
        if (result == 0)
            throw new CargoException("创建分类失败");
    }

    /**
     * 获取分组
     *
     * @return
     */
    @Cacheable(value = "cargoGroupCache")
    @Override
    public List<GroupDTO> getGroup() {
        List<GroupDTO> dtoList = new ArrayList<>();
        getGroupAll(0L, dtoList);
        return dtoList;
    }

    /**
     * 创建商品
     *
     * @param vo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createCargo(CreateCargoVO vo) {
        if (StringUtils.isEmpty(vo.getCargoName()) || StringUtils.isEmpty(vo.getDirection()) || StringUtils.isEmpty(vo.getIntro())) {
            throw new CargoException("请按规定填写");
        }
        if (Objects.isNull(vo.getSpecList()) || vo.getSpecList().isEmpty())
            throw new CargoException("请填写规格");
        CargoGroup group = groupMapper.selectGroupId(vo.getGroupId());
        if (Objects.isNull(group))
            throw new CargoException("分类不存在");

        CargoInfo cargoInfo = new CargoInfo();
        cargoInfo.setCargoId(SnowFlake.generateId());
        cargoInfo.setGroupId(vo.getGroupId());
        cargoInfo.setCargoName(vo.getCargoName());
        cargoInfo.setIntro(vo.getIntro());
        cargoInfo.setDirection(vo.getDirection());
        cargoInfo.setUnit(vo.getUnit());
        cargoInfo.setMainCoverUrl(vo.getMainCoverUrl());
        cargoInfo.setDealFlag(StatusType.NOT_DELETE);
        cargoInfo.setStatus(StatusType.NORMAL);
        infoMapper.insert(cargoInfo);
        //创建规格
        CargoSpec spec = new CargoSpec();
        for (CreateSpecVO specVO : vo.getSpecList()) {
            if (StringUtils.isEmpty(specVO.getSpecName()) || Objects.isNull(specVO.getUnitPrice()) || specVO.getUnitPrice() == 0)
                throw new CargoException("请按照规定填写");
            spec.setCargoId(cargoInfo.getCargoId());
            spec.setCoverUrl(specVO.getCoverUrl());
            spec.setDealFlag(StatusType.NOT_DELETE);
            spec.setSpecName(specVO.getSpecName());
            spec.setStock(specVO.getStock());
            spec.setUnitPrice(specVO.getUnitPrice());
            spec.setStatus(StatusType.NORMAL);
            specMapper.insert(spec);
        }

    }

    private void getGroupAll(Long upGroupId, List<GroupDTO> dtoList) {
        List<CargoGroup> groupList = groupMapper.selectUpGroupId(upGroupId);
        log.info("groupList ===>{}", groupList);
        for (CargoGroup group : groupList) {
            GroupDTO dto = new GroupDTO();
            dto.setGroupId(group.getGroupId());
            dto.setGroupName(group.getGroupName());
            dtoList.add(dto);
            List<GroupDTO> children = new ArrayList<>();
            dto.setChildren(children);
            getGroupAll(group.getGroupId(), children);
        }
    }

    @Override
    public List<BackCargoDTO> backCargo(String cargoName) {
        List<BackCargoDTO> list = infoMapper.selectBackCaro(cargoName);
        return list;
    }

    @Override
    public void revampGroup(RevampGroupVO vo) {
        CargoGroup existGroup = groupMapper.selectGroupId(vo.getGroupId());
        if (Objects.isNull(existGroup))
            throw new CargoException("要修改的分类不存在");
        if (!StringUtils.isEmpty(vo.getGroupName()) && !existGroup.getGroupName().equals(vo.getGroupName())) {
            CargoGroup groupName = groupMapper.selectGroupName(vo.getGroupName());
            if (!Objects.isNull(groupName))
                throw new CargoException("名称存在");
        }
        int result = groupMapper.updateGroup(vo);
        if (result == 0)
            throw new CargoException("出现问题了");
    }


}
