package com.lsjyy.nemesis.common.aop.user;

//import com.alibaba.fastjson.JSON;
//import com.lsjyy.nemesis.common.aop.log.Logging;
//import com.lsjyy.nemesis.common.domain.AjaxResult;
//import com.lsjyy.nemesis.common.utils.ServletUtils;
//import com.lsjyy.nemesis.common.utils.StringUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.OutputStream;
//import java.lang.reflect.Method;
//
///**
// * @Authoer LsjYy
// * @DATE 2020-03-07 21:00
// * @Description:
// */
//@Aspect
//@Component
//@Slf4j
//public class NeedLoginAspect {
//
//
//    @Before("@annotation(com.lsjyy.nemesis.common.aop.user.NeedLogin)")
//    public void checkNeedLogin(JoinPoint joinPoint) throws Exception {
//        String token = ServletUtils.getRequest().getHeader("Authorization");
//        if (StringUtils.isEmpty(token)) {
//            HttpServletResponse response = ServletUtils.getResponse();
//            response.setContentType("application/json;charset=UTF-8");
//            OutputStream out = response.getOutputStream();
//            String str = JSON.toJSONString(AjaxResult.warn("请去登录"));
//            out.write(str.getBytes("UTF-8"));
//            out.flush();
//            out.close();
//            throw new Exception();
//        }
//
//    }
//}
//
//
//