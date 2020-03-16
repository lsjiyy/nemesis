package com.lsjyy.nemesis.cargo.controller;

import com.github.pagehelper.Page;
import com.lsjyy.nemesis.cargo.exception.CargoException;
import com.lsjyy.nemesis.cargo.pojo.dto.BackCargoDTO;
import com.lsjyy.nemesis.cargo.pojo.dto.GroupDTO;
import com.lsjyy.nemesis.cargo.pojo.vo.CreateCargoVO;
import com.lsjyy.nemesis.cargo.pojo.vo.CreateGroupVO;
import com.lsjyy.nemesis.cargo.pojo.vo.RevampGroupVO;
import com.lsjyy.nemesis.cargo.service.CargoService;
import com.lsjyy.nemesis.common.aop.log.Logging;
import com.lsjyy.nemesis.common.control.BaseControl;
import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 19:48
 * @Description:
 */
@RestController
@Slf4j
public class CargoControl extends BaseControl {

    @Autowired
    private CargoService cargoService;


    /**
     * 创建商品分类
     *
     * @param vo
     * @return
     */
    @Logging(module = "cargo", operateExplain = "创建商品分类")
    @PostMapping("group")
    public AjaxResult createGroup(CreateGroupVO vo) {
        try {
            cargoService.createGroup(vo);
            //创建成功就调用刷新
            cargoService.getGroup();
            return AjaxResult.success();
        } catch (CargoException e) {
            return AjaxResult.warn(e.getMessage());
        } catch (Exception e) {
            log.error("###Exception ====>{}", e);
            return AjaxResult.error();
        }
    }

    /**
     * 获取分类
     *
     * @return
     */
    @GetMapping("group")
    public AjaxResult getGroup() {
        try {
            List<GroupDTO> dtoList = cargoService.getGroup();
            return AjaxResult.success(dtoList);
        } catch (Exception e) {
            log.error("###Exception ====>{}", e);
            return AjaxResult.error();
        }
    }

    /**
     * todo 修改分组
     *
     * @return
     */
    @Logging(module = "cargo", operateExplain = "修改分组")
    @PutMapping("/group")
    public AjaxResult revampGroup(RevampGroupVO vo) {
        try {
            cargoService.revampGroup(vo);
            return AjaxResult.success();
        } catch (CargoException e) {
            return AjaxResult.warn(e.getMessage());
        } catch (Exception e) {
            log.error("###Exception ===>{}", e);
            return AjaxResult.error();
        }
    }

    @Logging(module = "cargo", operateExplain = "创建商品")
    @PostMapping()
    public AjaxResult createCargo(CreateCargoVO vo) {
        try {
            cargoService.createCargo(vo);
            return AjaxResult.success();
        } catch (CargoException e) {
            return AjaxResult.warn(e.getMessage());
        } catch (Exception e) {
            log.error("###Exception ====>{}", e);
            return AjaxResult.error();
        }
    }

    /**
     * 后台获取
     *
     * @param cargoName
     * @return
     */
    @GetMapping("/list/back")
    public AjaxResult getList(String cargoName) {
        try {
            startPage();
            List<BackCargoDTO> dtoList = cargoService.backCargo(cargoName);
            log.info("dtoList ==>{}", dtoList);
            PageResult<BackCargoDTO> result = new PageResult<>(dtoList);
            return AjaxResult.success(result);
        } catch (Exception e) {
            log.error("###Exception ====>{}", e);
            return AjaxResult.error();
        }
    }
}
