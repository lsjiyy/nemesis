package com.lsjyy.nemesis.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.domain.log.RecordLogVO;
//import com.lsjyy.nemesis.common.mq.MsgProducer;
import com.lsjyy.nemesis.common.utils.IPUtil;
import com.lsjyy.nemesis.common.utils.ServletUtils;
import com.lsjyy.nemesis.common.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-13 11:25
 * @Description:
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    //@Autowired
   // private MsgProducer msgProducer;

    // 配置织入点
    @Pointcut("@annotation(com.lsjyy.nemesis.common.aop.Logging)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }


    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得注解
            Logging controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            recordLog(controllerLog, jsonResult);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    private void recordLog(Logging logging, Object result) {
        String sysUserId = ServletUtils.getRequest().getParameter("sysUserId");
        if (StringUtils.isEmpty(sysUserId)) {
            return;
        }
        RecordLogVO recordLogVO = new RecordLogVO();
        recordLogVO.setModule(logging.module());
        recordLogVO.setOperateType(logging.operateExplain());
        if (logging.isSaveRequestData()) {
            Map<String, String[]> stringMap = ServletUtils.getRequest().getParameterMap();
            recordLogVO.setArgs(StringUtil.dealMap(stringMap));
        }
        //记录时间
        recordLogVO.setRecordTime(new Timestamp(System.currentTimeMillis()));
        //操作人员Id
        recordLogVO.setSysUserId(sysUserId);
        //ip
        recordLogVO.setIp(IPUtil.getIpAddr(ServletUtils.getRequest()));
        //操作结果
        recordLogVO.setStatus(JSONObject.toJSONString(result));
       // msgProducer.sendOperateLogMsg(JSONObject.toJSONString(recordLogVO));
    }


    /**
     * 是否存在注解，如果存在就获取
     */
    private Logging getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Logging.class);
        }
        return null;
    }
}
