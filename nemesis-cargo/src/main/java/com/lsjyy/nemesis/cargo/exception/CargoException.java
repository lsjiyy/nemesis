package com.lsjyy.nemesis.cargo.exception;

import com.lsjyy.nemesis.common.exception.BasicException;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-15 15:26
 * @Description:
 */
public class CargoException extends BasicException {
    public CargoException(String msg) {
        super(msg, "cargo");
    }

}
