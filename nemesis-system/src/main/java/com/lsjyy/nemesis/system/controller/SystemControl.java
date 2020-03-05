package com.lsjyy.nemesis.system.controller;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.utils.IPUtil;
import com.lsjyy.nemesis.system.exception.SystemException;
import com.lsjyy.nemesis.system.pojo.dto.ServerStatusDTO;
import com.lsjyy.nemesis.system.pojo.dto.SysUserDTO;
import com.lsjyy.nemesis.system.pojo.vo.*;
import com.lsjyy.nemesis.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 11:00
 * @Description:
 */
@RestController
public class SystemControl {
    private static final Logger log = LoggerFactory.getLogger(SystemControl.class);

    @Autowired
    private SystemService systemService;

    /**
     * @param request
     * @param loginVO
     * @return
     */
    @PostMapping("/login")
    public AjaxResult sysUserLogin(HttpServletRequest request, SysUserLoginVO loginVO) {
        try {
            String ip = IPUtil.getIpAddr(request);
            SysUserDTO dto = systemService.login(loginVO, ip);
            return AjaxResult.success(dto);
        } catch (SystemException e) {
            log.info("module ===>{},msg ===>{}", e.getModule(), e.getMessage());
            return AjaxResult.warn(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("###Exception ===>{}", e);
            return AjaxResult.error();
        }
    }

    /**
     * 刷新配置接口缓存信息
     *
     * @return
     */
    @PostMapping("/refresh/interface")
    public AjaxResult refreshInterface() {
        try {
            systemService.refreshInterface();
            return AjaxResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("###Exception ===>{}", e);
            return AjaxResult.error();
        }
    }


    @GetMapping("/role/usr")
    public AjaxResult getUsrRole(){
        return AjaxResult.success(systemService.getUsrRole());
    }
}
