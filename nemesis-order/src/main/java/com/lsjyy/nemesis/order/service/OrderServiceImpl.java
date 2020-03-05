package com.lsjyy.nemesis.order.service;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.OrderStatus;
import com.lsjyy.nemesis.common.redis.RedisKey;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.common.utils.PrimaryKeyUtil;
import com.lsjyy.nemesis.order.dao.CargoInfoMapper;
import com.lsjyy.nemesis.order.dao.OrderInfoMapper;
import com.lsjyy.nemesis.order.pojo.CargoInfo;
import com.lsjyy.nemesis.order.pojo.OrderInfo;
import com.lsjyy.nemesis.order.pojo.vo.PlaceOrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-05 18:26
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CargoInfoMapper cargoInfoMapper;
    @Autowired
    private OrderInfoMapper orderMapper;

    @Override
    public void rushOrder(String message) {
        //解析参数
        PlaceOrderVO vo = JSONObject.parseObject(message, PlaceOrderVO.class);
        //查询是用户秒杀到
        Object orderObject = redisUtil.getStringValue(RedisKey.RUSH_ORDER + vo.getMouseId() + "_" + vo.getCargoId());
        if (!Objects.isNull(orderObject)) {
            return;
        }
        //判断是否拥有库存
        CargoInfo cargoInfo = cargoInfoMapper.selectByCargoId(vo.getCargoId());
        if (cargoInfo.getInventory() <= 0) {
            return;
        }
        log.info("3");
        //减库存 创建订单
        cargoInfoMapper.updateInventory(vo.getCargoId(), 1);
        OrderInfo orderInfo = new OrderInfo();
        //订单简介
        orderInfo.setOrderName(cargoInfo.getCargoName());
        //数量
        orderInfo.setCount(1);
        //实付
        orderInfo.setTotalMoney(cargoInfo.getUnitPrice());
        //用户Id
        orderInfo.setMouseId(vo.getMouseId());
        //订单状态
        orderInfo.setStatus(OrderStatus.WAIT);
        //商品Id
        orderInfo.setCargoId(vo.getCargoId());
        //订单Id
        orderInfo.setOrderId(PrimaryKeyUtil.generateKey("OD_"));
        //插入db
        orderMapper.insert(orderInfo);
        //订单存入缓存
       redisUtil.putString(RedisKey.RUSH_ORDER + vo.getMouseId() + "_" + vo.getCargoId(), orderInfo.getOrderId());
    }
}
