package com.lsjyy.nemesis.cargo.service;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.cargo.dao.CargoInfoMapper;
import com.lsjyy.nemesis.cargo.dao.CollectCargoMapper;
import com.lsjyy.nemesis.cargo.exception.CargoException;
import com.lsjyy.nemesis.cargo.pojo.CargoInfo;
import com.lsjyy.nemesis.cargo.pojo.dto.CargoSampleDTO;
import com.lsjyy.nemesis.cargo.pojo.dto.ClientCargoDTO;
import com.lsjyy.nemesis.cargo.pojo.vo.ClientCargoVO;
import com.lsjyy.nemesis.common.domain.StatusType;
import com.lsjyy.nemesis.common.redis.RedisKey;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:19
 * @Description:
 */
@Service
@EnableScheduling
public class CargoServiceImpl implements CargoService {
    private static final Logger log = LoggerFactory.getLogger(CargoServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CargoInfoMapper cargoInfoMapper;
    @Autowired
    private CollectCargoMapper collectMapper;

    //每10秒
    //@Scheduled(cron = " 0/40 * * * * ?")
    @Override
    public void cacheCargoList() {
        log.info("运行");
        List<CargoSampleDTO> dtoList = cargoInfoMapper.selectNormalSample();
        dtoList.forEach(dto -> {
            redisUtil.setValue(RedisKey.CARGO + dto.getCargoId(), JSONObject.toJSONString(dto));
        });
    }

    @Override
    public List<CargoSampleDTO> getCargoList() {
        List<CargoSampleDTO> dtoList = new ArrayList<>();
        Set<String> cargoSet = redisUtil.likeGet(RedisKey.CARGO + "*");
        List<Object> cargoObjList = redisUtil.multiStringGet(cargoSet);
        cargoObjList.forEach(cargoObj -> {
            CargoSampleDTO dto = JSONObject.parseObject(cargoObj.toString(), CargoSampleDTO.class);
            dtoList.add(dto);
        });
        return dtoList;
    }

    /**
     * 秒杀货物,初步测试 todo
     *
     * @param cargoId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rushCargo(String cargoId) {
        Object objDto = redisUtil.getValue(RedisKey.CARGO + cargoId);
        if (objDto == null) {
            return;
        }
        CargoSampleDTO dto = JSONObject.parseObject(objDto.toString(), CargoSampleDTO.class);
        if (dto != null) {
            if (dto.getInventory() == 0) {
                redisUtil.deleteKey(RedisKey.CARGO + cargoId);
                return;
            }
            //库存为0
            if (dto.getInventory() - 1 <= 0) {
                //删除缓存中的列表
                redisUtil.deleteKey(RedisKey.CARGO + cargoId);
                cargoInfoMapper.updateInventory(cargoId, 1);
                log.info("更新数据库,加入待支付订单");
            } else {
                //更新缓存内容
                dto.setInventory(dto.getInventory() - 1);
                redisUtil.setValue(RedisKey.CARGO + cargoId, JSONObject.toJSONString(dto));
                cargoInfoMapper.updateInventory(cargoId, 1);
                log.info("更新数据库,加入待支付订单");
            }
        }


    }

    @Override
    public ClientCargoDTO clientCargoInfo(ClientCargoVO vo) {
        CargoInfo cargoInfo = cargoInfoMapper.selectByCargoId(vo.getCargoId());
        if (cargoInfo == null || cargoInfo.getDealFlag() == StatusType.DELETE || cargoInfo.getStatus() != StatusType.NORMAL) {
            throw new CargoException("该货物不存在或已被下架");
        }
        ClientCargoDTO dto = new ClientCargoDTO(cargoInfo);
        if (!StringUtils.isEmpty(vo.getMouseId())) {
            boolean favorite = collectMapper.exist(vo.getCargoId(), vo.getMouseId());
            dto.setFavorite(favorite);
        }
        return dto;
    }
}
