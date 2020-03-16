package com.lsjyy.nemesis.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-13 13:41
 * @Description:
 */
public class ServletUtils {

    public static ServletRequestAttributes getAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest() {
        return getAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getAttributes().getResponse();
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }
}
