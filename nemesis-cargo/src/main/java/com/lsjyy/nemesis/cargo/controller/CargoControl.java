package com.lsjyy.nemesis.cargo.controller;

import com.lsjyy.nemesis.cargo.exception.CargoException;
import com.lsjyy.nemesis.cargo.pojo.dto.CargoSampleDTO;
import com.lsjyy.nemesis.cargo.pojo.dto.ClientCargoDTO;
import com.lsjyy.nemesis.cargo.pojo.vo.ClientCargoVO;
import com.lsjyy.nemesis.cargo.service.CargoService;
import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:48
 * @Description:
 */
@RestController
public class CargoControl {
    private static final Logger log = LoggerFactory.getLogger(CargoControl.class);

    @Autowired
    private CargoService cargoService;

    /**
     * 商品列表,从redis中取,若redis中没有,则返回空集合
     * 对缓存雪崩有一定的预防效果,列表不直接查询数据库
     *
     * @return
     */
    @PostMapping("/list")
    public AjaxResult getCargoList() {
        try {
            List<CargoSampleDTO> dtoList = cargoService.getCargoList();
            return AjaxResult.success(dtoList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    /**
     * 客户端获取货品详情
     *
     * @param vo
     * @return
     */
    @PostMapping("/client/info")
    public AjaxResult getCargoInfo(ClientCargoVO vo) {
        try {
            ClientCargoDTO dto = cargoService.clientCargoInfo(vo);
            return AjaxResult.success(dto);
        } catch (CargoException e) {
            log.info("module ===>{},msg ===>{}", e.getModule(), e.getMessage());
            return AjaxResult.warn(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    @GetMapping("/call")
    public AjaxResult cargoAnswer() {
        return AjaxResult.success();
    }
}
