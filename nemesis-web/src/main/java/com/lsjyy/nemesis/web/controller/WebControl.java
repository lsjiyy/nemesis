package com.lsjyy.nemesis.web.controller;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.redis.LockKey;
import com.lsjyy.nemesis.common.redis.RedisKey;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.web.exception.WebException;
import com.lsjyy.nemesis.web.service.WebService;
import com.lsjyy.nemesis.web.vo.PlaceOrderVO;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 21:11
 * @Description:
 */
@RestController
public class WebControl {
    private static final Logger log = LoggerFactory.getLogger(WebControl.class);

    @Autowired
    private WebService webService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/rush")
    public AjaxResult rushCargo(PlaceOrderVO vo) {
        RLock lock = redissonClient.getLock(LockKey.RS_LOCK + vo.getCargoId());
        try {
            Boolean cache = lock.tryLock(30, 15, TimeUnit.SECONDS);
            if (cache) {
                //查询是用户秒杀到
                Object orderObject = redisUtil.getStringValue(RedisKey.RUSH_ORDER + vo.getMouseId() + "_" + vo.getCargoId());
                if (!Objects.isNull(orderObject)) {
                    //该用户已抢购到 抛出异常
                    return AjaxResult.warn("该用户已重复秒杀");
                }
                //商品库存
                Object object = redisUtil.getStringValue(RedisKey.RUSH + vo.getCargoId());
                if (Objects.isNull(object)) {
                    //商品已被抢购完毕
                    return AjaxResult.warn("商品不存在");
                }
                long count = Long.valueOf(object.toString());
                if (count <= 0) {
                    return AjaxResult.warn("商品抢购完毕");
                }
                count = redisUtil.increment(RedisKey.RUSH + vo.getCargoId(), -1);
                if (count < 0) {
                    return AjaxResult.warn("商品抢购完毕");
                }
                webService.rushCargo(vo);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("###Exception ===>{}", e);
            return AjaxResult.error();
        } finally {
            lock.unlock();
        }
    }
}
