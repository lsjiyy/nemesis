package com.lsjyy.nemesis.cargo.service;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.lsjyy.nemesis.cargo.dao.CargoInfoMapper;
import com.lsjyy.nemesis.cargo.dao.CargoSlideMapper;
import com.lsjyy.nemesis.cargo.dao.CollectCargoMapper;
import com.lsjyy.nemesis.cargo.exception.CargoException;
import com.lsjyy.nemesis.cargo.pojo.CargoInfo;
import com.lsjyy.nemesis.cargo.pojo.CargoSlide;
import com.lsjyy.nemesis.cargo.pojo.dto.CargoSampleDTO;
import com.lsjyy.nemesis.cargo.pojo.dto.ClientCargoDTO;
import com.lsjyy.nemesis.cargo.pojo.vo.ClientCargoVO;
import com.lsjyy.nemesis.cargo.pojo.vo.CreateCargoVO;
import com.lsjyy.nemesis.common.domain.StatusType;
import com.lsjyy.nemesis.common.kafka.KafkaMessage;
import com.lsjyy.nemesis.common.kafka.KafkaMsgProducer;
import com.lsjyy.nemesis.common.redis.RedisKey;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.common.utils.PrimaryKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

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
    @Autowired
    private CargoSlideMapper slideMapper;

    //缓存库存
    @Scheduled(fixedRate = 90000)
    @Override
    public void cacheCargoInventory() {
        log.info("缓存库存");
        List<CargoSampleDTO> dtoList = cargoInfoMapper.selectNormalSample();
        dtoList.forEach(dto -> {
            redisUtil.putString(RedisKey.RUSH + dto.getCargoId(), dto.getInventory());
        });
    }

    @Override
    public List<CargoSampleDTO> getCargoList() {
        return null;
    }


    @Override
    public ClientCargoDTO clientCargoInfo(ClientCargoVO vo) {
        CargoInfo cargoInfo = cargoInfoMapper.selectById(vo.getCargoId());
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createCargo(CreateCargoVO vo) {
        if (StringUtils.isEmpty(vo.getCargoName()) || StringUtils.isEmpty(vo.getUnit()) || Objects.isNull(vo.getUnitPrice())) {
            throw new CargoException("请安照规定填写");
        }
        CargoInfo cargoInfo = new CargoInfo();
        //库存
        if (!Objects.isNull(vo.getInventory())) {
            cargoInfo.setInventory(vo.getInventory());
        }
        //单价
        cargoInfo.setUnitPrice(vo.getUnitPrice());
        //名称
        cargoInfo.setCargoName(vo.getCargoName());
        //详情
        cargoInfo.setDirection(vo.getDirection());
        //简介
        cargoInfo.setExplain(vo.getExplain());
        //单位
        cargoInfo.setUnit(vo.getUnit());
        //封面
        cargoInfo.setCoverUrl(vo.getCoverUrl());
        //cargoId
        cargoInfo.setCargoId(PrimaryKeyUtil.generateKey("CARGO_"));
        //状态
        cargoInfo.setStatus(StatusType.NORMAL);
        cargoInfo.setDealFlag(StatusType.NOT_DELETE);
        //保存
        CargoSlide slide = new CargoSlide();
        cargoInfoMapper.insert(cargoInfo);
        if (!vo.getSlideList().isEmpty() && vo.getSlideList().size() > 0) {
            vo.getSlideList().forEach(value -> {
                slide.setCargoId(cargoInfo.getCargoId());
                slide.setFileUrl(value);
                slide.setCreateTime(new Timestamp(System.currentTimeMillis()));
                slideMapper.insert(slide);
            });
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduceInventory(KafkaMessage message) throws Exception {
        cargoInfoMapper.updateInventory(message.getData(), -1);
        if (!StringUtils.isEmpty(message.getData())) {
            throw new Exception("ss");
        }
        //正常完成,删除此消息
        redisUtil.deleteKey("kafka" + message.getMessageId());
    }
}
