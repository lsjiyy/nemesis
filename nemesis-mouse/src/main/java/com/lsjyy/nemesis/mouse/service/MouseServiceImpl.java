package com.lsjyy.nemesis.mouse.service;


import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.domain.StatusType;
import com.lsjyy.nemesis.common.domain.mail.MailContentType;
import com.lsjyy.nemesis.common.domain.mail.MailSubject;
import com.lsjyy.nemesis.common.domain.mail.MailType;
import com.lsjyy.nemesis.common.domain.mail.SendMailVO;
import com.lsjyy.nemesis.common.domain.template.TemplateType;
import com.lsjyy.nemesis.common.jwt.JwtUtil;
import com.lsjyy.nemesis.common.jwt.Token;
import com.lsjyy.nemesis.common.kafka.KafkaMsgProducer;
import com.lsjyy.nemesis.common.redis.RedisKey;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.common.utils.EncryptUtil;
import com.lsjyy.nemesis.common.utils.PrimaryKeyUtil;
import com.lsjyy.nemesis.common.utils.RandomUtil;
import com.lsjyy.nemesis.mouse.dao.MouseInfoMapper;
import com.lsjyy.nemesis.mouse.exception.MouseException;
import com.lsjyy.nemesis.mouse.feign.SystemFeign;
import com.lsjyy.nemesis.mouse.pojo.MouseInfo;
import com.lsjyy.nemesis.mouse.pojo.dto.MouseInfoDto;
import com.lsjyy.nemesis.mouse.pojo.vo.CheckRegisterVO;
import com.lsjyy.nemesis.mouse.pojo.vo.MouseLoginVO;
import com.lsjyy.nemesis.mouse.pojo.vo.RegisterMouseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 12:10
 * @Description:
 */
@Service
public class MouseServiceImpl implements MouseService {
    private static final Logger log = LoggerFactory.getLogger(MouseService.class);

    @Autowired
    private MouseInfoMapper mouseMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private KafkaMsgProducer msgProducer;
    @Autowired
    private SystemFeign systemFeign;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterMouseVO vo) throws Exception {
        log.info("vo ===>{}", vo);
        //登录名是否重复
        boolean nameExist = mouseMapper.existLoginName(vo.getLoginName());
        if (nameExist) {
            throw new MouseException("该账号已注册过");
        }
        //邮箱是否重复
        boolean emailExist = mouseMapper.existEmail(vo.getEmail());
        if (emailExist) {
            throw new MouseException("该邮箱已被使用过");
        }
        //保存数据库
        MouseInfo mouseInfo = new MouseInfo();
        mouseInfo.setMouseId(PrimaryKeyUtil.generateKey("MU_"));
        //该账号未激活
        mouseInfo.setStatus(StatusType.UNACTIVATED);
        mouseInfo.setEmail(vo.getEmail());
        mouseInfo.setLoginName(vo.getLoginName());
        mouseInfo.setPassword(EncryptUtil.encrypt(vo.getPassword()));
        //发送邮件
        String code = RandomUtil.getUuid(6);
        redisUtil.putTimeString(RedisKey.EMAIL + vo.getEmail(), code, 15L, TimeUnit.MINUTES);
        SendMailVO mailVO = new SendMailVO(vo.getEmail(), code, MailSubject.REGISTER_CODE, MailContentType.HTML, TemplateType.EMAIL, MailType.SYSTEM);
        //msgProducer.sendEmailMsg(JSONObject.toJSONString(mailVO));
        //保存数据
        mouseMapper.insert(mouseInfo);
    }


    @Override
    public MouseInfoDto checkRegisterCode(CheckRegisterVO vo) throws Exception {
        MouseInfo mouseInfo = mouseMapper.selectByEmail(vo.getEmail());
        if (mouseInfo == null) {
            throw new MouseException("该邮箱未被使用");
        }
        //提取
        Object codeObj = redisUtil.getStringValue(RedisKey.EMAIL + vo.getEmail());
        if (Objects.isNull(codeObj)) {
            throw new MouseException("验证码已过期");
        }
        if (!vo.getCode().equals(codeObj.toString())) {
            throw new MouseException("验证码不正确");
        }
        //修改状态
        mouseMapper.updateStatus(mouseInfo.getMouseId(), StatusType.NORMAL);
        //返回信息,登录
        MouseInfoDto dto = new MouseInfoDto(mouseInfo);
        Map<String, Object> map = new HashMap<>();
        Token token = JwtUtil.generateToken(map);
        dto.setToken(token);
        return dto;

    }

    @Override
    public MouseInfoDto login(MouseLoginVO vo) throws Exception {
        MouseInfo mouseInfo = mouseMapper.selectByLoginName(vo.getLoginName());
        if (mouseInfo == null) {
            throw new MouseException("不存在的账号");
        }
        if (!EncryptUtil.encrypt(vo.getPassword()).equals(mouseInfo.getPassword())) {
            throw new MouseException("密码有误");
        }
        if (mouseInfo.getStatus() == StatusType.DELETE) {
            throw new MouseException("账号不正常,请联系管理员,或自行申诉");
        }
        //返回信息,登录
        MouseInfoDto dto = new MouseInfoDto(mouseInfo);
        Map<String, Object> map = new HashMap<>();
        AjaxResult ajaxResult = systemFeign.getUsrRole();
        map.put("ROLES", ajaxResult.get(AjaxResult.DATA_TAG));
        log.info("data ==>{}", ajaxResult.get(AjaxResult.DATA_TAG));
        Token token = JwtUtil.generateToken(map);
        dto.setToken(token);
        return dto;
    }

    @Override
    public void activityAccount(String mouseId) {
        MouseInfo mouseInfo = mouseMapper.selectByMouseId(mouseId);
        if (mouseInfo == null) {
            throw new MouseException("不存在的账号");
        }
        //发送邮件
        String code = RandomUtil.getUuid(6);
        redisUtil.putTimeString(RedisKey.EMAIL + mouseInfo.getEmail(), code, 15L, TimeUnit.MINUTES);
        SendMailVO mailVO = new SendMailVO(mouseInfo.getEmail(), code, MailSubject.REGISTER_CODE, MailContentType.HTML, TemplateType.EMAIL, MailType.SYSTEM);
        msgProducer.sendEmailMessage(JSONObject.toJSONString(mailVO));
    }
}
