package com.lsjyy.nemesis.mouse.controller;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.mouse.exception.MouseException;
import com.lsjyy.nemesis.mouse.pojo.dto.MouseInfoDto;
import com.lsjyy.nemesis.mouse.pojo.vo.CheckRegisterVO;
import com.lsjyy.nemesis.mouse.pojo.vo.MouseLoginVO;
import com.lsjyy.nemesis.mouse.pojo.vo.RegisterMouseVO;
import com.lsjyy.nemesis.mouse.service.MouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 12:05
 * @Description:
 */
@RestController
public class MouseControl {
    private static final Logger log = LoggerFactory.getLogger(MouseControl.class);

    @Autowired
    private MouseService mouseService;

    /**
     * 注册,异步发送邮件验证码
     *
     * @param vo
     * @return
     */
    @PostMapping("/register")
    public AjaxResult mouseRegister(RegisterMouseVO vo) {
        try {
            mouseService.register(vo);
            return AjaxResult.success();
        } catch (MouseException e) {
            return AjaxResult.warn(e.getMessage());
        } catch (Exception e) {
            return AjaxResult.error("内部服务器错误,请联系管理员");
        }
    }

    /**
     * 校验验证码
     *
     * @param vo
     * @return
     */
    @PostMapping("/check")
    public AjaxResult checkCode(CheckRegisterVO vo) {
        try {
            MouseInfoDto dto = mouseService.checkRegisterCode(vo);
            return AjaxResult.success(dto);
        } catch (MouseException e) {
            return AjaxResult.warn(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("###Exception ===>{}", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 登录,登录之后查看账号是否激活,未激活发送邮件激活
     *
     * @param vo
     * @return
     */
    @PostMapping("/login")
    public AjaxResult login(MouseLoginVO vo) {
        try {
            MouseInfoDto dto = mouseService.login(vo);
            return AjaxResult.success(dto);
        } catch (MouseException e) {
            return AjaxResult.warn(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("###Exception ===>{}", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 再次发送邮件
     *
     * @param mouseId
     * @return
     */
    @PostMapping("/activity")
    public AjaxResult activityAccount(String mouseId) {
        try {
            mouseService.activityAccount(mouseId);
            return AjaxResult.success();
        } catch (MouseException e) {
            return AjaxResult.warn(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("###Exception ===>{}", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    @GetMapping("/call")
    public AjaxResult mouseAnswer() {
        return AjaxResult.success();
    }


}
