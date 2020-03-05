package com.lsjyy.nemesis.web.exception;

import com.lsjyy.nemesis.common.exception.BasicException;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-05 18:17
 * @Description:
 */
public class WebException extends BasicException {
    public WebException(String msg) {
        super(msg, "web");
    }
}
