package com.lsjyy.nemesis.system.exception;

import com.lsjyy.nemesis.common.exception.BasicException;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 15:29
 * @Description:
 */
public class SystemException extends BasicException {
    public SystemException(String msg) {
        super(msg, "system");
    }
}
