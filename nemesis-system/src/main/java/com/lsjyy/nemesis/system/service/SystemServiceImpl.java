package com.lsjyy.nemesis.system.service;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.domain.log.RecordLogVO;
import com.lsjyy.nemesis.common.jwt.JwtUtil;
import com.lsjyy.nemesis.common.jwt.Token;
import com.lsjyy.nemesis.common.utils.EncryptUtil;
import com.lsjyy.nemesis.common.utils.PrimaryKeyUtil;
import com.lsjyy.nemesis.system.config.InitializeData;
import com.lsjyy.nemesis.system.dao.OperateHistoryMapper;
import com.lsjyy.nemesis.system.dao.SysUserMapper;
import com.lsjyy.nemesis.system.dao.SysUserRoleMapper;
import com.lsjyy.nemesis.system.exception.SystemException;
import com.lsjyy.nemesis.system.feign.MouseFeign;
import com.lsjyy.nemesis.system.mq.MsgReceiver;
import com.lsjyy.nemesis.system.pojo.OperateHistory;
import com.lsjyy.nemesis.system.pojo.SysUser;
import com.lsjyy.nemesis.system.pojo.SysUserRole;
import com.lsjyy.nemesis.system.pojo.dto.ServerStatusDTO;
import com.lsjyy.nemesis.system.pojo.dto.SysUserDTO;
import com.lsjyy.nemesis.system.pojo.dto.UserRoleInterfaceDTO;
import com.lsjyy.nemesis.system.pojo.vo.SysUserLoginVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 10:59
 * @Description:
 */
@Service
public class SystemServiceImpl implements SystemService {
    private static final Logger log = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private InitializeData initializeData;
    @Autowired
    private OperateHistoryMapper operateHistoryMapper;
    @Autowired
    private MouseFeign mouseFeign;


    @Override
    public SysUserDTO login(SysUserLoginVO vo, String ip) throws Exception {
        log.info("loginName ===>{}", vo.getLoginName());
        if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(vo.getLoginName()) || StringUtils.isEmpty(vo.getPassword())) {
            throw new SystemException("内容为空");
        }
        SysUser sysUser = userMapper.selectByLoginName(vo.getLoginName());
        if (sysUser.getStatus() != 0) {
            throw new SystemException("该账号被停用");
        }
        if (sysUser.getDealFlag() != 0) {
            throw new SystemException("该账号被删除,请联系管理员");
        }
        //密码判断
        if (!sysUser.getPassword().equals(EncryptUtil.encrypt(vo.getPassword()))) {
            throw new SystemException("密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        UserRoleInterfaceDTO interfaceDTO = userRoleMapper.selectUserInterface(sysUser.getSysUserId());
        map.put("ROLES", interfaceDTO.getInterfaceIdList());
        //该用户角色,角色的
        Token token = JwtUtil.generateToken(map);
        SysUserDTO dto = new SysUserDTO();

        dto.setUserName(sysUser.getUserName());
        dto.setSysUserId(sysUser.getSysUserId());
        dto.setToken(token);
        //更新登录信息
        updateSysUserLogin(sysUser.getSysUserId(), ip);
        return dto;
    }

    @Override
    public void refreshInterface() {
        initializeData.loadDataCache();
    }

    @Override
    public void recordLog(String content) {
        if (StringUtils.isEmpty(content)) {
            return;
        }
        RecordLogVO vo = JSONObject.parseObject(content, RecordLogVO.class);
        log.info("vo ===>{}", vo.toString());
        OperateHistory history = new OperateHistory(vo);
        history.setHistoryId(PrimaryKeyUtil.generateKey("LOG_"));
        operateHistoryMapper.insert(history);
    }

    @Override
    public List<ServerStatusDTO> getServerStatus() {
        List<ServerStatusDTO> dtos = new ArrayList<>();
        ServerStatusDTO dto = new ServerStatusDTO();
        dto.setServerName("mouse");
        AjaxResult result = mouseFeign.callServer();
        dto.setResult(result.get(AjaxResult.CODE_TAG).toString());
        dtos.add(dto);
        return dtos;
    }


    /**
     * 更新系统用户登录信息
     *
     * @param sysUserId
     * @param ip
     */
    public void updateSysUserLogin(String sysUserId, String ip) {
        userMapper.updateLogin(sysUserId, ip);
    }
}
