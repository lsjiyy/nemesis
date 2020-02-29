package com.lsjyy.nemesis.mouse.exception;

import com.lsjyy.nemesis.common.exception.BasicException;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 12:18
 * @Description:
 */
public class MouseException extends BasicException {
    public MouseException(String msg) {
        super(msg, "mouse");
    }
}
