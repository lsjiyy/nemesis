package com.lsjyy.nemesis.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 15:20
 * @Description:
 */
@Data
@NoArgsConstructor
public class BasicException extends RuntimeException {
    private String message;
    private String module;

    public BasicException(String message,String module){
        this.message=message;
        this.module= module;
    }


}
